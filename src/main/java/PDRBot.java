import adapter.sender.ChatSender;
import adapter.sender.SenderTelegrambots;
import handlers.MainMenuStrategy;
import handlers.Strategy;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PDRBot extends SenderTelegrambots {
    private static final String PATH = "src/main/resources/bot.properties";
    private final static Properties PROPERTIES = getProperty();
    private final static String TELEGRAM_BOT_NAME = PROPERTIES.getProperty("bot.name");
    private final static String TELEGRAM_BOT_TOKEN = PROPERTIES.getProperty("bot.token");
    private final Map<Long, Strategy> mapOfStrategy = new HashMap<>();

    // Також можна замутити це гавно через конструктор
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new PDRBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            long chatId;
            if (update.hasCallbackQuery()) {
                chatId = update.getCallbackQuery().getMessage().getChatId();
            } else if (update.hasMessage()) {
                chatId = update.getMessage().getChatId();
            } else {
                return;
            }
            Strategy currentStrategy = mapOfStrategy.get(chatId);
            if (currentStrategy == null) {
                currentStrategy = new MainMenuStrategy(new ChatSender(this, chatId));
            }
            currentStrategy.onUpdateReceived(update);
            mapOfStrategy.put(chatId, currentStrategy.getStrategy());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return TELEGRAM_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return TELEGRAM_BOT_TOKEN;
    }

    private static Properties getProperty() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH)) {
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return properties;
    }
}






