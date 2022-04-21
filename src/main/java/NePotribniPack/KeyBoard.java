package NePotribniPack;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class KeyBoard {
// м'яко кажучи криво але працює, треба буде все пере усвідомити,
     static List<InlineKeyboardButton> setInline(){

        InlineKeyboardButton abb = new InlineKeyboardButton();
        abb.setText("Червоний гандон");
        abb.setCallbackData("1");
        InlineKeyboardButton spaka = new InlineKeyboardButton();
        spaka.setText("Правий підор");
        spaka.setCallbackData("2");

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(abb);
        buttons.add(spaka);

        return buttons;



    }

    public static SendMessage Klavka(Update update){
        // ініціалізація кейборд інлайн
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(setInline()));

        SendMessage klavka = new SendMessage();
        klavka.setChatId(update.getMessage().getChatId().toString());
        klavka.setText("Спроба");

        klavka.setReplyMarkup(inlineKeyboardMarkup);



        return klavka;
    }
}
