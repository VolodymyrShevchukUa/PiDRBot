import NePotribniPack.Photo;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PDRBot extends TelegramLongPollingBot {
    // Поля класу
    private static final String TelegramBotName = "PDRbot";
    private static final String TelegramBotToken = "5184808348:AAGqn7MBsuOsdTCGtnA1GrN6VwmavE0m8LY";
    private int countAnser;
    private int testcount = 0;
    Set<Integer> messenger = new HashSet<>(); // Херня для перевірки на кількість натискань

    // вхід в програму
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new PDRBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void onUpdateReceived2(Update update) {

        if (update.hasCallbackQuery()) {
            callBack(update.getCallbackQuery());
        } else {
            message(update.getMessage());
        }

    }
    // не статичні методи, які при цьому всьому в мейні проініціалізуються, треба буде запитатись як? оскільки через об'єкти не викликались, можливо метод регистер бот
    @Override
    public void onUpdateReceived(Update update) {
        try {
            onUpdateReceived2(update);
            // вивід ексепшину
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // ініціалізація????? колекції

    // для роботи з коллбеками
    private void callBack(CallbackQuery callbackQuery) {
        Integer messageId = callbackQuery.getMessage().getMessageId();
        long chatID = callbackQuery.getMessage().getChatId();


        if (testcount == keyBoardList.COUNT_QUESTION) {
            try {
                execute(new SendMessage().builder().chatId(chatID + "").text(countAnser + "%").build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            // нахуя тут цей ретурн?, пхду щоб воно сюди більше не заходило навіть якщо тест каунт знову дорівнюватиме каунт квещн
        return;
        }
        if(!messenger.contains(messageId)){
            try {
                execute(getPhoto(chatID).get(testcount));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            testcount++;
            validateAnswer(callbackQuery);
            messenger.add(messageId);
        }
    }
    private void validateAnswer(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("true")) {
            countAnser += 5;
        }

    }

    public static List<SendPhoto> getPhoto(Long chatID) {

        List<SendPhoto> kek = new ArrayList<>();
        for (InputFile i : keyBoardList.getFile()) {
            kek.add(new SendPhoto().builder().photo(i)
                    .chatId(chatID + "")
       //             .replyMarkup(keyBoardList.klavka.get(keyBoardList.index))
                    .build());
            // Воно не працює
  //          Question.index++;
        }
        return kek;
    }



    // для роботи з месягами
    private void message(Message message) {
        SendMessage command = new SendMessage();
        command.setChatId(message.getChatId()+"");
        long chatID = message.getChatId();
        if (message.getText().startsWith("/")) {
            switch (message.getText()){
                case "/test":
                    command.setText("Lets start");
                case "/rank":
                    command.setText(("Gomos"));
                default:
                    command.setText("unknow command");
            }
            try {
                execute(command);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (message.getText().equals("/test")) {
            testcount = 0;
            try {
                execute(Photo.PhotoRules(chatID + ""));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }


    // поки що не працює, треба нормально це все зробити
    private String getCurrencyButton(Update update) {
        return update.getCallbackQuery().getData().equals("true") ? "✅" : "X";
    }

    @Override
    public String getBotUsername() {
        return TelegramBotName;
    }

    @Override
    public String getBotToken() {
        return TelegramBotToken;
    }


}








