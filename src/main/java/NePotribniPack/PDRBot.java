//import entity.Ticket;
//import utils.json.JSONToQuestion;
//import entity.Question;
//import utils.json.QuestionToJSON;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//
//import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//public class PDRBot extends TelegramLongPollingBot {
//    private static final String TelegramBotName = "PDRbot";
//    private static final String TelegramBotToken = "5184808348:AAGqn7MBsuOsdTCGtnA1GrN6VwmavE0m8LY";
//    private int countAnser;
//    private int COUNT_QUESTION = 20;
//    Set<Integer> messenger = new HashSet<>(); // Херня для перевірки на кількість натискань
//    private List<Question> questionsList = new JSONToQuestion(QuestionToJSON.PATH).getQuestions();
//    private Ticket currentTicket;
//
//    public static void main(String[] args) {
//
//        try {
//            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//            telegramBotsApi.registerBot(new PDRBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//    public void onUpdateReceived2(Update update) {
//
//        if (update.hasCallbackQuery()) {
//            callBack(update.getCallbackQuery());
//        } else {
//            message(update.getMessage());
//        }
//
//    }
//    @Override
//    public void onUpdateReceived(Update update) {
//        try {
//            onUpdateReceived2(update);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void callBack(CallbackQuery callbackQuery) {
//        Integer messageId = callbackQuery.getMessage().getMessageId();
//        long chatID = callbackQuery.getMessage().getChatId();
//
//
//        if (currentTicket.isEnd()) {
//            try {
//                execute(new SendMessage().builder().chatId(chatID + "").text(countAnser + "%").build());
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//            // нахуя тут цей ретурн?, пхду щоб воно сюди більше не заходило навіть якщо тест каунт знову дорівнюватиме каунт квещн
//        return;
//        }
//        if(!messenger.contains(messageId)){
//            try {
//                execute(currentTicket.getNextSendPhoto(chatID));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//            validateAnswer(callbackQuery);
//            messenger.add(messageId);
//        }
//    }
//    private void validateAnswer(CallbackQuery callbackQuery) {
//        if (callbackQuery.getData().equals("true")) {
//            countAnser += 5;
//        }
//
//    }
//
//    // для роботи з месягами
//    private void message(Message message) {
//        SendMessage command = new SendMessage();
//        command.setChatId(message.getChatId()+"");
//        long chatID = message.getChatId();
//        if (message.getText().startsWith("/")) {
//            switch (message.getText()){
//                case "/test":
//                    command.setText("Lets start");
//                    currentTicket = new Ticket(questionsList,COUNT_QUESTION);
//                    try {
//                        execute(currentTicket.getNextSendPhoto(chatID));
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case "/rank":
//                    command.setText(("Gomos"));
//                    break;
//                default:
//                    command.setText("unknow command");
//                    break;
//            }
//            try {
//                execute(command);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//
//    // поки що не працює, треба нормально це все зробити
//    public static String getCurrencyButton(Update update,Integer in) {
//        return update.getCallbackQuery().getData().equals("true") ?in + "✅" : in+ "X";
//    }
//
//    @Override
//    public String getBotUsername() {
//        return TelegramBotName;
//    }
//
//    @Override
//    public String getBotToken() {
//        return TelegramBotToken;
//    }
//
//
//}
//
//
//
//
//
//
//
//
