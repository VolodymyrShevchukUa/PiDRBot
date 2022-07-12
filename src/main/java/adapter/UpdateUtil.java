package adapter;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateUtil {

    public static long getChatId(Update update) {
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
}
