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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PDRBot extends TelegramLongPollingBot {
    private static final String TelegramBotName = "PDRbot";
    private static final String TelegramBotToken = "5184808348:AAGqn7MBsuOsdTCGtnA1GrN6VwmavE0m8LY";
    private int countAnser;
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
    // ініціалізація????? колекції
    Set<Integer> messenger = new HashSet<>();
    // для роботи з коллбеками
    private void callBack(Update update) {
        long chatID = update.getCallbackQuery().getMessage().getChatId();
        if (testcount == 10) {
            try {
                execute(new SendMessage().builder().chatId(chatID + "").text(countAnser + "%").build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        return;
        }

        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        // застосовується колекція Інтеджер яку ти не знаєш лох
        if(!messenger.contains(messageId)){
            try {
                execute(getPhoto(chatID).get(testcount));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            testcount++;
            validateAnswer(update);
            messenger.add(messageId);
        }




    }
    public static List<SendPhoto> getPhoto(Long chatID) {

        List<SendPhoto> kek = new ArrayList<>();
        for (InputFile i : Question.getFile()) {
            kek.add(new SendPhoto().builder().photo(i)
                    .chatId(chatID + "")
                    .replyMarkup(keyBoardList.klavka.get(Question.index))
                    .build());
            // Воно не працює
  //          Question.index++;
        }
        return kek;
    }

    private void validateAnswer(Update update) {
        if (update.getCallbackQuery().getData().equals("true")) {
            countAnser += 5;
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

        if (update.hasCallbackQuery()) {
            callBack(update);
        } else {
            message(update);
        }

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




}








