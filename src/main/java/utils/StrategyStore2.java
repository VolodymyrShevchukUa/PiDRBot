package utils;

import adapter.sender.ChatSender;
import adapter.sender.SenderTelegrambots;
import handlers.MainMenuStrategy;
import handlers.Strategy;

public class StrategyStore2 extends AutoCleaningMap<Long, Strategy> {
    private final SenderTelegrambots sender;

    public StrategyStore2(SenderTelegrambots sender) {
        this.sender = sender;
    }

    @Override
    protected Strategy factory(Long chatId) {
        return new MainMenuStrategy(new ChatSender(sender, chatId));
    }
}
