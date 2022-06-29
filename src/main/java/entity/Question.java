package entity;

import adapter.message.MessageI;
import adapter.message.PhotoMessage;
import adapter.message.TextMessage;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    public static final String NULL_IMAGE = "null image";
    private static final String CAPTION_LABEL = "Caption";
    private static final String CORRECT_BUTTON_LABEL = "CorrectButton";
    private static final String URL_LABEL = "Url";
    private static final String COUNT_OF_BUTTON_LABEL = "CountOfButton";
    private static final String THEME_LABEL = "theme";
    public final String caption;
    private final String url;
    private final int correctButton;
    private final int countOfButton;

    private int theme;

    public Question(JSONObject jsonObject) {
        url = jsonObject.getString(URL_LABEL);
        correctButton = jsonObject.getInt(CORRECT_BUTTON_LABEL);
        countOfButton = jsonObject.getInt(COUNT_OF_BUTTON_LABEL);
        caption = jsonObject.getString(CAPTION_LABEL);
        theme = jsonObject.getInt(THEME_LABEL);
    }

    public Question(String image, int correctButton, String caption, int button) {
        this.url = image;
        this.correctButton = correctButton;
        this.caption = caption;
        this.countOfButton = button;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    private boolean hasPhoto() {
        return !url.equals(NULL_IMAGE);
    }

    public MessageI createMessage() {
        InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardButtonList();
        MessageI messageI;
        if (hasPhoto()) {
            messageI = new PhotoMessage(caption, new InputFile(url), inlineKeyboardMarkup);
        } else {
            messageI = new TextMessage(caption, inlineKeyboardMarkup);
        }
        return messageI;
    }

    private InlineKeyboardMarkup createInlineKeyboardButtonList() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        int correct = correctButton + 1;
        for (int i = 1; i < countOfButton; i++) {
            buttons.add(InlineKeyboardButton.builder()
                    .callbackData((i == correct) + "")
                    .text(i + "")
                    .build());
        }
        in.setKeyboard(Collections.singletonList((buttons)));
        return in;
    }


    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CAPTION_LABEL, caption);
        jsonObject.put(CORRECT_BUTTON_LABEL, correctButton);
        jsonObject.put(URL_LABEL, url);
        jsonObject.put(COUNT_OF_BUTTON_LABEL, countOfButton);
        jsonObject.put(THEME_LABEL, theme);
        return jsonObject;
    }
}
