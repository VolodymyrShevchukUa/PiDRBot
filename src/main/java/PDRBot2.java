import adapter.sender.SenderTelegrambots;
import handlers.MainMenuStrategy;
import handlers.Strategy;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.util.Properties;


public class PDRBot2 extends SenderTelegrambots {
    private static final String PATH = "src/main/resources/bot.properties";
    private final Properties propertie = getProperty();
    private final String TelegramBotName = propertie.getProperty("bot.name");
    private final String TelegramBotToken = propertie.getProperty("bot.token");
    private Strategy currentStrategy = new MainMenuStrategy(this);

    // Також можна замутити це гавно через конструктор
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new PDRBot3());
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

    private Properties getProperty() {

        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream(PATH);
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return properties;
    }
}








