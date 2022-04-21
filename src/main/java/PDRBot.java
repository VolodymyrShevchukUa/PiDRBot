import NePotribniPack.Photo;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PDRBot extends TelegramLongPollingBot {
    private static final String TelegramBotName = "PDRbot";
    private static final String TelegramBotToken = "5184808348:AAGqn7MBsuOsdTCGtnA1GrN6VwmavE0m8LY";
    private int countAnser;
    private int count;
    private int testcount = 0;

    // вхід в програму
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new PDRBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return TelegramBotName;
    }

    @Override
    public String getBotToken() {
        return TelegramBotToken;
    }

    // для роботи з коллбеками
    private void callBack(Update update) {
        long chatID = update.getCallbackQuery().getMessage().getChatId();
        if (update.hasCallbackQuery()) {
            testcount++;
            try {
                execute(getPhoto(update, chatID).get(testcount));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


        if (countUpdate(update)) {
            long callBackChatId = update.getCallbackQuery().getMessage().getChatId();
            try {
                execute(new SendMessage().builder().chatId(callBackChatId + "").text(getAnswer(update) + "%").build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }


    // для роботи з месягами

    private void message(Update update) {
        long chatID = update.getMessage().getChatId();
        if (update.getMessage().getText().startsWith("/")) {
            try {
                execute(CommandBotEasy.HelpCommand(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.getMessage().getText().equals("/test")) {
            testcount = 0;
            try {
                execute(Photo.PhotoRules(update, chatID + ""));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }


    public void onUpdateReceived2(Update update) {
        // Запуск відправки фото
        // Всю логіку розкидую по методАм, того що null point
        // Вот тут зараз собсна перевірка цьої хуйні
        //     update.getMessage().getChatId();


        if (update.hasCallbackQuery()) {
            callBack(update);
        } else {
            message(update);
        }

// ТЕРНАРНИЙ ОПЦІОН
//        long chatID = update.hasMessage() ?
//                update.getMessage().getChatId()
//                : update.getCallbackQuery().getMessage().getChatId();

    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            onUpdateReceived2(update);
            // вивід ексепшину
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Треба подумати як реалізувати так щоб кнопка натискалась лише раз
    // поки не працює
    public Boolean countUpdate(Update update) {

        boolean a = false;
        if (update.hasCallbackQuery()) {
            this.count++;
        }
        if (this.count > 9) {
            a = true;
        }

        return a;
    }


    public Integer getAnswer(Update update) {
// сюди чогось не заходить, вже заходить бо треба вибирати колбек дата, не хуйово
        // Якщо що я шарю що може бути булеан тру, це стрінг, всьо нормально так і треба
        if (Objects.equals(update.getCallbackQuery().getData(), "true")) {
            this.countAnser += 5;
        }
        return this.countAnser;
    }

    public static List<SendPhoto> getPhoto(Update update, Long ChatID) {

        List<SendPhoto> kek = new ArrayList<>();
        for (InputFile i : Question.getFile())
            kek.add(new SendPhoto().builder().photo(i)
                    .chatId(ChatID + "")
                    .replyMarkup(keyBoardList.klavka.get(Question.index))
                    .build());
        Question.index++;

        return kek;
    }
}








