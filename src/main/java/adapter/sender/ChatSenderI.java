package adapter.sender;

import adapter.message.MessageI;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ChatSenderI {
    Message sendText(String text);
    Message execute(MessageI messageI);
}
