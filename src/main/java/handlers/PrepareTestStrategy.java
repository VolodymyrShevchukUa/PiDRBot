package handlers;

import adapter.message.TextMessage;
import adapter.sender.ChatSenderI;
import entity.Question;
import entity.quiz.Quiz;
import entity.quiz.QuizWithMarks;
import entity.quiz.QuizWithTime;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import utils.QuestionCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PrepareTestStrategy implements Strategy {
    private final ChatSenderI sender;
    private Quiz quiz = null;
    private Integer countOfQuestion = null;
    private String nameOfSubject = null;
    private boolean isRealTest = false;
    private boolean isWithTime = false;
    private Menu currentMenu = Menu.MENU1;

    public PrepareTestStrategy(ChatSenderI sender) {
        this.sender = sender;
    }

    @Override
    public Strategy getStrategy() {
        return quiz == null ? this : new TestStrategy(sender, quiz);
    }

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        switch (currentMenu) {
            case MENU1:
                menu1(text);
                break;
            case MENU2:
                menu2(text);
                break;
            case MENU3:
                menu3(text);
                break;
            case MENU4:
                menu4(text);
                break;
            case ARE_YOU_READY:
                areYouReady(text);
                break;
        }
    }

    private void createQuiz() {
        Queue<Question> questionsList = nameOfSubject == null ?
                QuestionCache.getQuestionsListWithSize(countOfQuestion)
                : QuestionCache.getQuestionsListWithSizeAndSubject(countOfQuestion, nameOfSubject);
        int numberOfAttempts = isRealTest ? 2 : questionsList.size();
        quiz = isWithTime ?
                new QuizWithTime(questionsList, sender, numberOfAttempts)
                : new QuizWithMarks(questionsList, sender, numberOfAttempts);
        quiz.sendFirstQuestion();
    }

    public void sendButtonsForMenu1() {
        //#TODO
        sender.sendText("зроби нормальні кнопки 1");
    }

    private void menu1(String userAnswer) {
        switch (userAnswer) {
            case "всі питання":
                currentMenu = Menu.MENU3;
                sendButtonsForMenu3();
                break;
            case "питання по  темам":
                currentMenu = Menu.MENU2;
                sendButtonsForMenu2();
                break;
            case "реальний тест":
                isRealTest = true;
                countOfQuestion = 20;
                createQuiz();
                currentMenu = Menu.ARE_YOU_READY;
                sendButtonsForAreYouReady();
                break;
            default:
                //#TODO
                break;
        }
    }


    private void sendButtonsForMenu2() {
        //#TODO
        sender.sendText("зроби нормальні кнопки 2");
    }

    private void menu2(String userAnswer) {
        if (QuestionCache.SET_OF_SUBJECT.contains(userAnswer)) {
            nameOfSubject = userAnswer;
            currentMenu = Menu.MENU3;
            sendButtonsForMenu3();
        }
    }

    private void sendButtonsForMenu3() {
        //#TODO
        sender.sendText("зроби нормальні кнопки 3");
        sendButtons();
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

    private void menu3(String userAnswer) {
        try {
            countOfQuestion = Integer.parseInt(userAnswer);
        } catch (NumberFormatException e) {
            sender.execute(new TextMessage("Ви ввели не вірне число"));
            sendButtonsForMenu3();
            return;
        }
        if (countOfQuestion == 10 || countOfQuestion == 20 || countOfQuestion == 30 || countOfQuestion == 40) {
            currentMenu = Menu.MENU4;
            sendButtonsForMenu4();
        } else {
            sender.execute(new TextMessage("Ви ввели не вірне число"));
            sendButtonsForMenu3();
        }
    }

    private void sendButtonsForMenu4() {
        sender.sendText("зроби нормальні кнопки 4");
        //#TODO
    }

    private void menu4(String userAnswer) {
        switch (userAnswer) {
            case "без обмеження":
                isWithTime = false;
            case "з обмежденням":
                sendButtonsForAreYouReady();
                currentMenu = Menu.ARE_YOU_READY;
                break;
            default:
                //#TODO
                break;
        }
    }

    private void sendButtonsForAreYouReady() {
        sender.sendText("зроби нормальні кнопки 5");
        //#TODO
    }

    private void areYouReady(String userAnswer) {
        switch (userAnswer) {
            case "yes":
                createQuiz();
                quiz.sendFirstQuestion();
                break;
            case "nope":
                sendButtonsForAreYouReady();
                break;
            default:
                //#TODO
                break;
        }
    }

    private enum Menu {
        MENU1,
        MENU2,
        MENU3,
        MENU4,
        ARE_YOU_READY,
    }
}
