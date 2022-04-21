package NePotribniPack;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Collections;

public class Photo {

    public static SendPhoto PhotoRules(Update update,String chatId){


        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile("https://images.app.goo.gl/KP4sEMN9FozFuF7L7"));
        sendPhoto.setCaption("Чо каво епта");
        sendPhoto.setReplyMarkup(InKeyBoard.keyboard());
        return sendPhoto;
    }
}
