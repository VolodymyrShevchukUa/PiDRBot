import adapter.sender.SenderTelegrambots;
import handlers.Strategy;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import utils.MyProperties;
import utils.StrategyStore;

public class PDRBot extends SenderTelegrambots {
    private final StrategyStore strategyStore = new StrategyStore(this);

    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new PDRBot());
        } catch (TelegramApiException e) {
            //#TODO
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            long chatId = getChatId(update);
            Strategy currentStrategy = strategyStore.getStrategyByChatId(chatId);
            currentStrategy.onUpdateReceived(update);
            Strategy newStrategy = currentStrategy.getStrategy();
            if (currentStrategy != newStrategy) {
                strategyStore.saveNewStrategyByChatId(chatId, newStrategy);
            }
        } catch (Exception e) {
            //#TODO
            e.printStackTrace();
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






