package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class PhotoMessage extends SendPhoto implements MessageI {
    public PhotoMessage(String caption, InputFile inputFile, InlineKeyboardMarkup inlineKeyboardMarkup) {
        this(caption,inputFile);
        setReplyMarkup(inlineKeyboardMarkup);
    }
    public PhotoMessage(String caption, InputFile inputFile){
        setCaption(caption);
        setPhoto(inputFile);
    }

}
