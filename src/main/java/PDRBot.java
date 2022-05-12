import adapter.sender.SenderTelegrambots;
import handlers.MainMenuStrategy;
import handlers.Strategy;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PDRBot extends SenderTelegrambots {
    private static final String PATH = "src/main/resources/bot.properties";
    private final static Properties PROPERTIES = getProperty();
    private final static String TELEGRAM_BOT_NAME = PROPERTIES.getProperty("bot.name");
    private final static String TELEGRAM_BOT_TOKEN = PROPERTIES.getProperty("bot.token");
    private Strategy currentStrategy = new MainMenuStrategy(this);

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
            currentStrategy.onUpdateReceived(update);
            currentStrategy = currentStrategy.getStrategy();
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


//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        sendHideKeyboard(message.getChatId(), message.getMessageId());
//    }
//
//    private void sendHideKeyboard(Long chatId, Integer messageId) {
//        SendMessage sendMessageRequest = new SendMessage();
//        sendMessageRequest.setChatId(chatId + "");
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//
//        List<KeyboardRow> commands = new ArrayList<>();
//        KeyboardRow commandRow = new KeyboardRow();
//        commandRow.add(" --> ");
//        commandRow.add("Zalupka");
//        commandRow.add("Milupka");
//        commandRow.add("Zalupka");
//        commandRow.add("Milupka");
//        commandRow.add("Zalupka");
//        commandRow.add("Milupka");
//        commands.add(commandRow);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        replyKeyboardMarkup.setKeyboard(commands);
//        replyKeyboardMarkup.setSelective(true);
//        sendMessageRequest.setReplyMarkup(replyKeyboardMarkup);
//        sendMessageRequest.setText("test3");
//
//        try {
//            execute(sendMessageRequest);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//}






