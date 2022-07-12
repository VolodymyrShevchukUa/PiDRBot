package utils;

import org.glassfish.grizzly.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AutoCleaningMap<K, V> {
    private final Map<K, Pair<V, Long>> map = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock read = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock write = lock.writeLock();

    protected AutoCleaningMap() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::cleanUpMap, 7, 7, TimeUnit.DAYS);
    }

    public V computeIfAbsent(K key) {
        try {
            read.lock();
            return map.computeIfAbsent(key, this::createPairByKey).getFirst();
        } finally {
            read.unlock();
        }
    }

    private Pair<V, Long> createPairByKey(K key) {
        return new Pair<>(factory(key), System.currentTimeMillis());
    }

    protected abstract V factory(K key);


    public void put(K key, V value) {
        try {
            read.lock();
            map.put(key, new Pair<>(value, System.currentTimeMillis()));
        } finally {
            read.unlock();
        }
    }

    private void cleanUpMap() {
        //#TODO: add Logger
        try {
            write.lock();
            for (Map.Entry<K, Pair<V, Long>> ent : map.entrySet()) {
                long lastTimeExecuted = ent.getValue().getSecond();
                if (TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - lastTimeExecuted) > 7) {
                    map.remove(ent.getKey());
                }
            }
        } finally {
            write.unlock();
        }
    }

}
