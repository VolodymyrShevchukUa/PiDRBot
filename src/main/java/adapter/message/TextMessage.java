package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class TextMessage extends SendMessage implements MessageI {
    public TextMessage(long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        this(chatId,text);
        setReplyMarkup(inlineKeyboardMarkup);
    }

    public TextMessage(long chatId, String text) {
        setText(text);
        setChatId(chatId+"");
    }
}
