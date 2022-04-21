package NePotribniPack;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InKeyBoard {

    public static InlineKeyboardMarkup keyboard() {
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        InlineKeyboardButton but1 = new InlineKeyboardButton();
        InlineKeyboardButton but2 = new InlineKeyboardButton();
        InlineKeyboardButton but3 = new InlineKeyboardButton();
        InlineKeyboardButton but4 = new InlineKeyboardButton();

        but1.setText("Варіант А");
        but2.setText("Варіант Б");
        but3.setText("Варіант В");
        but4.setText("Варіант Г");

        but1.setCallbackData("A");
        but2.setCallbackData("Б");
        but3.setCallbackData("True");
        but4.setCallbackData("Г");

        List<InlineKeyboardButton> firstline = new ArrayList<>();
        firstline.add(but1);
        firstline.add(but2);
        firstline.add(but3);
        firstline.add(but4);

        ini.setKeyboard(Collections.singletonList(firstline));

        return ini;
    }
    public static InlineKeyboardMarkup keyBoard2 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("True").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("A").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;
    }
    public static InlineKeyboardMarkup keyBoard3 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("True").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("A").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static InlineKeyboardMarkup keyBoard4 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("True").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("A").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static InlineKeyboardMarkup keyBoard5 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("True").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("A").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static InlineKeyboardMarkup keyBoard6 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("True").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static InlineKeyboardMarkup keyBoard7 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("True").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("A").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static InlineKeyboardMarkup keyBoard8 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("True").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static InlineKeyboardMarkup keyBoard9 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("True").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static InlineKeyboardMarkup keyBoard10 (){
        InlineKeyboardMarkup ini = new InlineKeyboardMarkup();
        List <InlineKeyboardButton> line = new ArrayList<>();
        line.add(new InlineKeyboardButton().builder()
                .text("A").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("B").callbackData("True").build());
        line.add(new InlineKeyboardButton().builder()
                .text("C").callbackData("A").build());
        line.add(new InlineKeyboardButton().builder()
                .text("D").callbackData("A").build());
        ini.setKeyboard(Collections.singletonList(line));
        return ini ;}
    public static List<InlineKeyboardMarkup> klava (){
        List <InlineKeyboardMarkup> a = new ArrayList<>();
        a.add(InKeyBoard.keyBoard2());
        a.add(InKeyBoard.keyBoard3());
        a.add(InKeyBoard.keyBoard4());
        a.add(InKeyBoard.keyBoard5());
        a.add(InKeyBoard.keyBoard6());
        a.add(InKeyBoard.keyBoard7());
        a.add(InKeyBoard.keyBoard8());
        a.add(InKeyBoard.keyBoard9());
        a.add(InKeyBoard.keyBoard10());
        return  a;


    }

    }