package handlers;


import adapter.sender.ChatSenderI;
import handlers.test.TestStrategy;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MainMenuStrategy implements Strategy {
    private final ChatSenderI sender;//PDRBot
    private Strategy nextStrategy = this;

    public MainMenuStrategy(ChatSenderI sender) {
        this.sender = sender;
    }

    @Override
    public Strategy getStrategy() {
        return nextStrategy;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        switch (message.getText()) {
            case "/start":
                TestStrategy testStrategy = new TestStrategy(sender);
                testStrategy.sendButtons();
                nextStrategy = testStrategy;
                break;
            case "/help":
            default:
                sender.sendText( "/help - опис команд, /start - почати тестування, вибір кількості питань, /stop - зупинка тесту");
                break;
        }
    }
}

