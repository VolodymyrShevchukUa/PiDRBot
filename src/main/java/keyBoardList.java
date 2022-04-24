import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class keyBoardList {
    // Ініціалізація посилань
    public static final List<String> url = getUrl();
    public static final List<InputFile> File = getFile();
    public static final int COUNT_QUESTION = 10;
    public static int index = 0;
    // Ліст посилань
    public static List<String> getUrl() {

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

    // Ліст де мутиться з посилань файл
    public static List<InputFile> getFile() {
        List<InputFile> kek = new ArrayList<>();
        for (String u : url) {
            kek.add(new InputFile(u));
        }
        return kek;
    }

    public static InlineKeyboardMarkup keyBoardobj() {
        List<InlineKeyboardButton> anal = new ArrayList<>();
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        for (int i = 0; i < 4; i++) {
            anal.add(new InlineKeyboardButton().builder()
                    .callbackData("false")
                    .text("Button")
                    .build());
        }
        in.setKeyboard(Collections.singletonList(anal));
        return in;
    }

    public static List<InlineKeyboardMarkup> getMarkupListTemplate() {
        List<InlineKeyboardMarkup> in = new ArrayList<>();
        for (int i = 0; i < COUNT_QUESTION; i++) {
            in.add(keyBoardobj());
        }
        return in;
    }

    static List<Integer> getPenis() {
        List<Integer> penis1 = new ArrayList<>();
        penis1.add(2);
        penis1.add(3);
        penis1.add(1);
        penis1.add(0);
        penis1.add(2);
        penis1.add(3);
        penis1.add(2);
        penis1.add(1);
        penis1.add(0);
        penis1.add(2);
        return penis1;
    }


    // З цим лістом повний морок
    private static void setListValue(List<InlineKeyboardMarkup> markupList, List<Integer> trueAnswer) {
        int i = 0;
        for(int answer : trueAnswer){

            markupList.get(i++).getKeyboard().get(0).get(answer).setCallbackData("true");

        }
    }

    // не працює як має бути, але ми не здаємось





}
