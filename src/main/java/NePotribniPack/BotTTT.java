package NePotribniPack;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;


public class BotTTT extends TelegramLongPollingBot {

    private final static String NAME_OF_BOT = "PDRbot";
    private final static String TOKEN = "5184808348:AAGqn7MBsuOsdTCGtnA1GrN6VwmavE0m8LY";

    public static void main(String[] args) {
        new BotTTT().run();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(sendInlineKeyBoardMessage(update.getMessage().getChatId() + ""));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//        try {
//            execute(new SendMessage().builder().text("Sosaty").replyMarkup(NePotribniPack.InKeyBoard.keyboard()).chatId(update.getMessage().getChatId().toString()).build());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
        SendMessage test = new SendMessage();
        test.setChatId(update.getMessage().getChatId()+"");
        test.setText(update.getCallbackQuery().toString());


        try {
            execute(test);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
//        if (update.hasCallbackQuery()) {
//            try {
//                execute(new SendMessage().builder().text("Працює").chatId(update.getMessage().getChatId().toString()).build());
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
    }
    private void run() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return NAME_OF_BOT;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
    public static SendMessage sendInlineKeyBoardMessage(String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Тык");
        inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");
        inlineKeyboardButton2.setText("Тык2");
        inlineKeyboardButton2.setCallbackData("Button \"Тык2\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().builder().text("Fi4a").callbackData("CallFi4a").build());
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().builder().chatId(chatId).text("Пример").replyMarkup(inlineKeyboardMarkup).build();
    }
}