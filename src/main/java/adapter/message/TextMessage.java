package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class TextMessage extends SendMessage implements MessageI {
    public TextMessage(String text, ReplyKeyboard replyKeyboard) {
        this(text);
        setReplyMarkup(replyKeyboard);
    }

    public TextMessage(String text) {
        setText(text);
    }
}
