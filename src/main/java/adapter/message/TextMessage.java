package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TextMessage extends SendMessage implements MessageI {
    public TextMessage(String text, ReplyKeyboard replyKeyboard) {
        this(text);
        setReplyMarkup(replyKeyboard);
    }

    public TextMessage(String text, List<List<String>> nameOfButtons) {
        this(text);
        List<KeyboardRow> commands = new ArrayList<>();
        for (List<String> nameOfButtonsByRows : nameOfButtons) {
            KeyboardRow rowOfButtons = new KeyboardRow();
            for (String nameOfButton : nameOfButtonsByRows) {
                rowOfButtons.add(nameOfButton);
            }
            commands.add(rowOfButtons);
        }
        setReplyMarkup(new ReplyKeyboardMarkup(commands, true, true, true, null));
    }

    public TextMessage(String text) {
        setText(text);
    }
}
