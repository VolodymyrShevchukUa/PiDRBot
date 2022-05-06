package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TextMessage extends SendMessage {
    TextMessage(Long chatID, String text) {
        super();
        setText(text);
        setChatId(chatID.toString());
    }
}
