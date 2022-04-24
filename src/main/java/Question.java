import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.awt.*;
// Екземпляр класу questions матиме два параметри,
// Їх ми пробуєм записати в масив [] об'єктів questions {} в JSON
public class Question {
    private String url ;
    private Integer correctButon;

    public Integer getCorrectButon() {
        return correctButon;
    }

    public void setCorrectButon(Integer correctButon) {
        this.correctButon = correctButon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
