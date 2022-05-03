package handlers;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Strategy {
    Strategy getStrategy();
    void onUpdateReceived(Update update) throws TelegramApiException;


}
