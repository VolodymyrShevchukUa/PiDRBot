import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class keyBoardList {

    static List<String> buttonsName = new ArrayList<>();
    static {
        setListValue(markupList());
        setButtonName();
    }

    static List<InlineKeyboardMarkup> klavka = markupList();

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

    public static void main(String[] args) {
        System.out.println(keyBoardobj().getKeyboard().get(0).size());
    }

    public static List<InlineKeyboardMarkup> markupList() {
        List<InlineKeyboardMarkup> in = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            in.add(keyBoardobj());
        }

        return in;
    }
    // True
    static List<Integer> penis = getPenis();
    static List<Integer> getPenis(){
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

    public static void setListValue(List<InlineKeyboardMarkup> markupList) {
        List<Integer> zalypa = getPenis();
        for (int l = 0; l<10 ;l++) {
            markupList.get(l).getKeyboard().get(0).get(zalypa.get(l)).setCallbackData("true");
        }
    }
   public static void  setButtonName(){
        buttonsName.add(0,"Варіант А");
        buttonsName.add(1,"Варіант Б");
        buttonsName.add(2,"Варіант В");
        buttonsName.add(3,"Варіант Г");
        for (InlineKeyboardMarkup i: markupList()) {
            for (int r = 0 ; r < 4 ; r++)
            i.getKeyboard().get(0).get(r).setText(buttonsName.get(r));
        }
    }

}
