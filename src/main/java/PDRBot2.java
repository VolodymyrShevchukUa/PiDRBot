import handlers.MainMenuStrategy;
import handlers.Strategy;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class PDRBot2 extends TelegramLongPollingBot {
    private static final String TelegramBotName = "PDRbot";
    private static final String TelegramBotToken = "5184808348:AAGqn7MBsuOsdTCGtnA1GrN6VwmavE0m8LY";
    private Strategy currentStrategy = new MainMenuStrategy(this);
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new PDRBot2());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            currentStrategy.onUpdateReceived(update);
            currentStrategy = currentStrategy.getStrategy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return TelegramBotName;
    }

    @Override
    public String getBotToken() {
        return TelegramBotToken;
    }


}








