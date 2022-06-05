package utils;

import adapter.sender.ChatSender;
import adapter.sender.SenderTelegrambots;
import handlers.MainMenuStrategy;
import handlers.Strategy;
import org.glassfish.grizzly.utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StrategyStore {
    private final SenderTelegrambots sender;
    private final Map<Long, Pair<Strategy, Long>> mapOfStrategy = new HashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final ReentrantReadWriteLock.ReadLock read = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock write = lock.writeLock();

    public StrategyStore(SenderTelegrambots sender) {
        this.sender = sender;
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::cleanUpMap, 7, 7, TimeUnit.DAYS);
    }

    public Strategy getStrategyByChatId(long chatId) {
        try {
            read.lock();
            return mapOfStrategy.computeIfAbsent(chatId, this::createMainMenuStrategy).getFirst();
        } finally {
            read.unlock();
        }
    }

    private Pair<Strategy, Long> createMainMenuStrategy(Long chatId) {
        return new Pair<>(new MainMenuStrategy(new ChatSender(sender, chatId)), System.currentTimeMillis());
    }


    public void saveNewStrategyByChatId(long chatId, Strategy newStrategy) {
        try {
            read.lock();
            mapOfStrategy.put(chatId, new Pair<>(newStrategy, System.currentTimeMillis()));
        } finally {
            read.unlock();
        }
    }

    private void cleanUpMap() {
        //#TODO: add Logger
        try {
            write.lock();
            for (Map.Entry<Long, Pair<Strategy, Long>> ent : mapOfStrategy.entrySet()) {
                long lastTimeExecuted = ent.getValue().getSecond();
                if (TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - lastTimeExecuted) > 7) {
                    mapOfStrategy.remove(ent.getKey());
                }
            }
        } finally {
            write.unlock();
        }
    }

}
