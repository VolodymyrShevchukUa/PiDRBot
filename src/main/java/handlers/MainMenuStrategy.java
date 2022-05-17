package handlers;


import adapter.sender.ChatSenderI;
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
                PrepareTestStrategy nextStrategy = new PrepareTestStrategy(sender);
                nextStrategy.sendButtonsForMenu1();
                this.nextStrategy = nextStrategy;
                break;
            case "/help":
            default:
                sender.sendText( "/help - опис команд, /start - почати тестування, вибір кількості питань, /stop - зупинка тесту");
                break;
        }
    }
}

