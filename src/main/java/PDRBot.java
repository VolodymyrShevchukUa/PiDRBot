import adapter.sender.SenderTelegrambots;
import handlers.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import utils.AutoCleaningMap;
import utils.MyProperties;
import utils.StrategyStore;
import utils.StrategyStore2;

public class PDRBot extends SenderTelegrambots {

    private static final Logger logger = LoggerFactory.getLogger(PDRBot.class);
    private final AutoCleaningMap<Long, Strategy> strategyStore = new StrategyStore2(this);

    public static void main(String[] args) {
        logger.info("Bot started");
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new PDRBot());
        } catch (TelegramApiException e) {
            logger.error("ERROR in Main method", e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            long chatId = getChatId(update);
            Strategy currentStrategy = strategyStore.computeIfAbsent(chatId);
            currentStrategy.onUpdateReceived(update);
            Strategy newStrategy = currentStrategy.getStrategy();
            if (currentStrategy != newStrategy) {
                strategyStore.put(chatId, newStrategy);
            }
        } catch (Exception e) {
            logger.error("ERROR in PDRBot.onUpdateReceived method", e);
        }
    }

    private static long getChatId(Update update) {
        long chatId;
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
        } else {
            throw new UnsupportedOperationException("not implemented yet");
        }
        return chatId;
    }

    @Override
    public String getBotUsername() {
        return MyProperties.getTelegramBotName();
    }

    @Override
    public String getBotToken() {
        return MyProperties.getTelegramBotToken();
    }
}






