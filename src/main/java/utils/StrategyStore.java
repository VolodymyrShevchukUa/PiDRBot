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

public class StrategyStore {
    private final SenderTelegrambots sender;
    private final Map<Long, Pair<Strategy, Long>> mapOfStrategy = new HashMap<>();

    public StrategyStore(SenderTelegrambots sender) {
        this.sender = sender;
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::cleanUpMap, 7, 7, TimeUnit.DAYS);
    }

    public Strategy getStrategyByChatId(long chatId) {
        return mapOfStrategy.computeIfAbsent(chatId, k -> new Pair<>(new MainMenuStrategy(new ChatSender(sender, chatId)), System.currentTimeMillis())).getFirst();
    }

    public void saveNewStrategyByChatId(long chatId, Strategy newStrategy) {
        mapOfStrategy.put(chatId, new Pair<>(newStrategy, System.currentTimeMillis()));
    }

    private void cleanUpMap() {
        //#TODO: add Logger
        synchronized (mapOfStrategy) {
            for (Map.Entry<Long, Pair<Strategy, Long>> ent : mapOfStrategy.entrySet()) {
                long lastTimeExecuted = ent.getValue().getSecond();
                if (TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - lastTimeExecuted) > 7) {
                    mapOfStrategy.remove(ent.getKey());
                }
            }
        }
    }

}
