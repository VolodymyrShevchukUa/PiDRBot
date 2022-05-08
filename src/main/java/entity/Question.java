package entity;

import adapter.message.PhotoMessage;
import adapter.message.MessageI;
import adapter.message.TextMessage;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import utils.json.QuestionToJSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Екземпляр класу questions матиме два параметри,
// Їх ми пробуєм записати в масив [] об'єктів questions {} в JSON
public class Question {
    private final String caption;
    private final String url;
    private final Integer correctButton;
    private final int countOfButton;

    public Question(JSONObject jsonObject) {
        url = jsonObject.getString(QuestionToJSON.URL);
        correctButton = jsonObject.getInt(QuestionToJSON.CORRECT_BUTTON);
        countOfButton = jsonObject.getInt(QuestionToJSON.COUNT_OF_BUTTON);
        caption = jsonObject.getString(QuestionToJSON.CAPTION);
    }

    public Question(String image, int correctButton, String caption, int button) {
        this.url = image;
        this.correctButton = correctButton;
        this.caption = caption;
        this.countOfButton = button;
    }

    public Integer getCorrectButon() {
        return correctButton;
    }
    
    public String getUrl() {
        return url;
    }

    public String getCaption() {
        return caption;
    }

    public int getCountOfButton() {
        return countOfButton;
    }

    public boolean hasPhoto(){
        return !url.equals(QuestionToJSON.NULL_IMAGE);
    }

    public MessageI createMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardButtonList(getCountOfButton(), getCorrectButon());
        MessageI messageI;
        if (hasPhoto()) {
            messageI = new PhotoMessage(chatId, getCaption(), new InputFile(getUrl()), inlineKeyboardMarkup);
        } else {
            messageI = new TextMessage(chatId, getCaption(), inlineKeyboardMarkup);
        }
        return messageI;
    }

    private InlineKeyboardMarkup createInlineKeyboardButtonList(int countOfButton, int trueButton) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        for (int i = 1; i < countOfButton; i++) {
            buttons.add(InlineKeyboardButton.builder()
                    .callbackData(i == trueButton ? "true" : "false")
                    .text(i + "✅")
                    .build());
        }
        in.setKeyboard(Collections.singletonList((buttons)));
        return in;
    }


}
