package adapter.sender;

import adapter.message.MessageI;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Sender {
    Message sendText(long chatID, String gomos);

    Message execute(MessageI messageI);
}
