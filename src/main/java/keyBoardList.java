import org.telegram.telegrambots.meta.api.methods.StopMessageLiveLocation;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class keyBoardList {
    // Ініціалізація посилань
    public static int index = 0;
    private static List<String> buttonsName = getButtonsName();

    private static List<String> getButtonsName() {
        List<String> get = new ArrayList<>();
        get.add("Варіант А");
        get.add("Варіант Б");
        get.add("Варіант В");
        get.add("Варіант Г");
        return get;
    }


    public static InlineKeyboardMarkup keyBoardobj() {
        List<InlineKeyboardButton> anal = new ArrayList<>();
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        for (int i = 0; i < 4; i++) {
            anal.add(new InlineKeyboardButton().builder()
                    .callbackData("false")
                    .text(buttonsName.get(i))
                    .build());
        }
        in.setKeyboard(Collections.singletonList(anal));
        return in;
    }
    // ПОДУМАЙ ЧИ ЗМОЖЕШ ТИ ТУТ ЗРОБИТИ РЕКУРСІЮ????
    public static List<InlineKeyboardMarkup> getMarkupListTemplate(int COUNT_QUESTION) {
        List<InlineKeyboardMarkup> in = new ArrayList<>();

        for (int i = 0; i < COUNT_QUESTION; i++) {
            in.add(keyBoardobj());
        }


        return in;
    }
// Вроді як працює
 //   public static void main(String[] args) {
   //     setButtonName(getMarkupListTemplate(20));
     //   System.out.println(getMarkupListTemplate(20));
   // }


    // З цим лістом повний морок


    // Не подобається що він статік але той підар в якому визивається тоже статичний, того треба подумати як це зробити
    private static void setButtonName(List<InlineKeyboardMarkup> markupList) {
        // не впевнений чи це працює ,лягаю спати, завтра перевірю
        for (InlineKeyboardMarkup i : markupList) {
            for (int a = 0;a<4;a++){
                i.getKeyboard().get(0).get(a).setText(buttonsName.get(a));
            }

        }

        // не працює як має бути, але ми не здаємось


    }
}
