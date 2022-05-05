package entity;

import org.json.JSONObject;
import utils.json.QuestionToJSON;

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


}
