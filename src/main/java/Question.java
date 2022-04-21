import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.ArrayList;
import java.util.List;

// Тут повинен бути зорганізований масив питань
// Можливо варто зробити конструктор Квещн і туди запихати сендфото...)
// Чого він ніколи не юзається блять

public class Question {
    // Так правильно
    public static final List<String> url = getUrl();
    public static final List<InputFile> File = getFile();
    public static int index = 0;




    public static List<String> getUrl() {
        // можливо щось з посиланнями, якась нєвєдома херня
        List<String> url = new ArrayList<>();
        url.add("https://images.app.goo.gl/mdSnsv5AuzhZQMt16");
        url.add("https://images.app.goo.gl/PyQ6vBb4i9CcD1yn9");
        url.add("https://images.app.goo.gl/mdSnsv5AuzhZQMt16");
        url.add("https://images.app.goo.gl/mdSnsv5AuzhZQMt16");
        url.add("https://images.app.goo.gl/X39QjnfqV9LJhhr96");
        url.add("https://images.app.goo.gl/wzkFajJirKJ7b8KCA");
        url.add("https://images.app.goo.gl/jaZDJbwDi1fGn3Ct9");
        url.add("https://images.app.goo.gl/XJfr1WnQMXqcRMn76");
        url.add("https://images.app.goo.gl/xJJSmVBxYciojknR6");
        url.add("https://images.app.goo.gl/xdeSFGMyufwgFQ9VA");
        return url;
    }

    public static List<InputFile> getFile() {
        List<InputFile> kek = new ArrayList<>();
        for (String u : url) {
            kek.add(new InputFile(u));
        }
        return kek;
    }



}
