package handlers.test;

import adapter.message.TextMessage;
import adapter.sender.ChatSenderI;
import entity.Question;
import entity.Ticket;
import handlers.MainMenuStrategy;
import handlers.Strategy;
import entity.quiz.Quiz;
import entity.quiz.QuizWithTime;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import utils.json.JSONToQuestion;
import utils.json.QuestionToJSON;

import java.util.ArrayList;
import java.util.List;

public class TestStrategy implements Strategy {
    private final ChatSenderI sender;
    private Quiz quiz;
    private Strategy strategy = this;
    private static final List<Question> questionsList = new JSONToQuestion(QuestionToJSON.PATH).getQuestions();


    public TestStrategy(ChatSenderI sender) {
        this.sender = sender;
    }

    @Override
    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isQuizStarted() {
        return quiz != null;
    }

    private void processMessage(Update update) {
        String userAnswer = update.getMessage().getText();
        if (!isQuizStarted()) {
            tryToCreateQuiz(userAnswer);
        } else {
            if (userAnswer.equals("/stop")) {
                goToMainMenu();
                if (quiz != null) {
                    quiz.sendResult();
                }
            }
        }
    }

    public void sendButtons() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> commands = new ArrayList<>();
        KeyboardRow commandRow = new KeyboardRow();
        KeyboardRow commandRow2 = new KeyboardRow();
        commandRow.add("10");
        commandRow.add("20");
        commandRow2.add("30");
        commandRow2.add("40");
        commands.add(commandRow2);
        commands.add(commandRow);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(commands);
        replyKeyboardMarkup.setSelective(true);
        TextMessage sendMessageRequest = new TextMessage("Оберіть будь ласка кількість питань", replyKeyboardMarkup);
        sender.execute(sendMessageRequest);
    }

    private void tryToCreateQuiz(String userAnswer) {
        int countOfQuestion;
        try {
            countOfQuestion = Integer.parseInt(userAnswer);
        } catch (NumberFormatException e) {
            sender.execute(new TextMessage("Ви ввели не вірне число"));
            sendButtons();
            return;
        }
        if (countOfQuestion == 10 || countOfQuestion == 20 || countOfQuestion == 30 || countOfQuestion == 40) {
            quiz = new QuizWithTime(new Ticket(questionsList, countOfQuestion).getQueueOfTicketMessages(), sender, 2);
            quiz.sendFirstQuestion();
        } else {
            sender.execute(new TextMessage( "Ви ввели не вірне число"));
            sendButtons();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            processCallbackQuery(update.getCallbackQuery());
        } else {
            processMessage(update);
        }
    }

    private void processCallbackQuery(CallbackQuery callbackQuery) {
        quiz.processCallbackQuery(callbackQuery);
        if (quiz.isEnd()) {
            goToMainMenu();
        }
    }

    private void goToMainMenu() {
        strategy = new MainMenuStrategy(sender);
    }
}

