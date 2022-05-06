package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class PhotoMessage extends SendPhoto {
    PhotoMessage(Long chatID, String text,  InputFile photo) {
        super();
        setCaption(text);
        setChatId(chatID.toString());
        setPhoto(photo);
    }

    PhotoMessage( Long chatID,  String text,  InputFile photo,  InlineKeyboardMarkup inlineKeyboardMarkup) {
        this(chatID, text, photo);
        setReplyMarkup(inlineKeyboardMarkup);
    }
}
