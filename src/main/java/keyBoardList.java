import org.telegram.telegrambots.meta.api.methods.StopMessageLiveLocation;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class keyBoardList {
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


}
