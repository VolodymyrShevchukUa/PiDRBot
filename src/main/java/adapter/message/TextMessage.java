package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class TextMessage extends SendMessage implements MessageI {
    public TextMessage(long chatId, String text, ReplyKeyboard replyKeyboard) {
        this(chatId,text);
        setReplyMarkup(replyKeyboard);
    }

    public TextMessage(long chatId, String text) {
        setText(text);
        setChatId(chatId+"");
    }
}
