package handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Strategy {
    Strategy getStrategy();
    void onUpdateReceived(Update update);

}
