package handlers;


import adapter.sender.Sender;
import handlers.test.TestStrategy;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MainMenuStrategy implements Strategy {
    private final Sender sender;//PDRBot
    private Strategy nextStrategy = this;

    public MainMenuStrategy(Sender sender) {
        this.sender = sender;
    }

    @Override
    public Strategy getStrategy() {
        return nextStrategy;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        long chatID = message.getChatId();
        if(message.hasText()){
        switch (message.getText()) {
            case "/start":
                TestStrategy testStrategy = new TestStrategy(sender);
                testStrategy.sendButtons(chatID);
                nextStrategy = testStrategy;
                break;
            case "/help":
            default:
                sender.sendText(chatID, "/help - опис команд, /start - почати тестування, вибір кількості питань, /stop - зупинка тесту");
                break;
        }
        }else{
            sender.sendText(chatID,"Ти ідіот?");
        }
    }
}

