import com.fasterxml.jackson.core.JsonParser;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.awt.*;
// Екземпляр класу questions матиме два параметри,
// Їх ми пробуєм записати в масив [] об'єктів questions {} в JSON
public class Question {
    private String caption;
    private String url ;
    private Integer correctButton;
    private int countOfButton;
    Question(String url,Integer correctButon,String caption ){
        this.url = url;
        this.correctButton = correctButon;
        this.caption = caption;
    }

    public Question(String image, int correct, String caption, int button) {
        this(image,correct,caption);
        this.countOfButton = button;
    }

    public Integer getCorrectButon() {
        return correctButton;
    }

    public void setCorrectButon(Integer correctButon) {
        this.correctButton = correctButon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getCountOfButton() {
        return countOfButton;
    }
}
