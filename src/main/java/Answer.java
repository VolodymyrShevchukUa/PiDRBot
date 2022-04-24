import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answer {

    private static final int COUNT_QUESTION = 20;

    private InlineKeyboardMarkup createKeyboard(int countOfFirstLine) {

        List<InlineKeyboardButton> button = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        for (int i = 0; i < countOfFirstLine; i++) {
            button.add(new InlineKeyboardButton().builder()
                    .callbackData("false")
                    .text("deffault")
                    .build());
        }
        keyboardMarkup.setKeyboard(Collections.singletonList(button));
        return keyboardMarkup;
    }

    private List<InlineKeyboardMarkup> getKeyboardTemplate() {
        List<InlineKeyboardMarkup> keyboardButtonList = new ArrayList<>();
        for (int i = 0; i < COUNT_QUESTION; i++) {
            keyboardButtonList.add(createKeyboard(4));
        }
        return keyboardButtonList;
    }

    // Це вот треба допилювати з цього місця
    private void setKeyboardCallBack(List<InlineKeyboardMarkup> Template, Integer answerNumber, String callBackData) {
        for (InlineKeyboardMarkup markup : Template) {
            int i = 0;
            Template.get(i++).getKeyboard().get(0).get(answerNumber).setCallbackData(callBackData);
        }
    }





}