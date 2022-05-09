package adapter.message;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class PhotoMessage extends SendPhoto implements MessageI {
    public PhotoMessage(long chatId, String caption, InputFile inputFile, InlineKeyboardMarkup inlineKeyboardMarkup) {
        this(chatId,caption,inputFile);
        setReplyMarkup(inlineKeyboardMarkup);
    }
    public PhotoMessage(long chatId, String caption, InputFile inputFile){
        setChatId(chatId+"");
        setCaption(caption);
        setPhoto(inputFile);
    }
}
