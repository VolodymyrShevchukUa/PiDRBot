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
    private Menu1 menu1 = new Menu1();
    private Menu2 menu2 = new Menu2();
    private Menu3 menu3 = new Menu3();
    private Menu4 menu4 = new Menu4();
    private AreYouReadyMenu areYouReadyMenu = new AreYouReadyMenu();


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
                menu1.menu1(text);
                break;
            case MENU2:
                menu2.menu2(text);
                break;
            case MENU3:
                menu3.menu3(text);
                break;
            case MENU4:
                menu4.menu4(text);
                break;
            case ARE_YOU_READY:
                areYouReadyMenu.areYouReady(text);
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
    }

    public void sendButtonsForMenu1() {
        menu1.sendButtonsForMenu1();
    }

    private void sendButtonsForMenu2() {
        menu2.sendButtonsForMenu2();
    }

    private void sendButtonsForMenu3() {
        menu3.sendButtonsForMenu3();
    }

    private void sendButtonsForMenu4() {
        menu4.sendButtonsForMenu4();
    }

    private void sendButtonsForAreYouReady() {
        areYouReadyMenu.sendButtonsForAreYouReady();
    }

    private enum Menu {
        MENU1,
        MENU2,
        MENU3,
        MENU4,
        ARE_YOU_READY,
    }

    private class Menu1 {
        private static final String ALL_QUESTION = "всі питання";
        private static final String QUESTION_BY_SUBJECT = "питання по темам";
        private static final String REAL_TEST = "реальний тест";

        private void sendButtonsForMenu1() {
            List<List<String>> buttons = new ArrayList<>();
            List<String> firstRow = new ArrayList<>();
            firstRow.add(ALL_QUESTION);
//            firstRow.add(QUESTION_BY_SUBJECT);
            List<String> secondRow = new ArrayList<>();
            secondRow.add(REAL_TEST);
            buttons.add(firstRow);
            buttons.add(secondRow);
            TextMessage buttonsForMenu1 = new TextMessage("Оберіть режим", buttons);
            sender.execute(buttonsForMenu1);
        }

        private void menu1(String userAnswer) {
            switch (userAnswer) {
                case ALL_QUESTION:
                    currentMenu = Menu.MENU3;
                    sendButtonsForMenu3();
                    break;
                case QUESTION_BY_SUBJECT:
                    currentMenu = Menu.MENU2;
                    sendButtonsForMenu2();
                    break;
                case REAL_TEST:
                    isRealTest = true;
                    isWithTime = true;
                    countOfQuestion = 20;
                    currentMenu = Menu.ARE_YOU_READY;
                    sendButtonsForAreYouReady();
                    break;
                default:
                    sendButtonsForMenu1();
                    break;
            }
        }
    }


    private class Menu2 {
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
    }


    private class Menu3 {
        private void sendButtonsForMenu3() {
            List<List<String>> buttons = new ArrayList<>();
            List<String> firstRow = new ArrayList<>();
            firstRow.add("10");
            firstRow.add("20");
            List<String> secondRow = new ArrayList<>();
            secondRow.add("30");
            secondRow.add("40");
            buttons.add(firstRow);
            buttons.add(secondRow);
            TextMessage buttonsForMenu1 = new TextMessage("Оберіть кількість питань", buttons);
            sender.execute(buttonsForMenu1);
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
    }

    private class Menu4 {
        private static final String WITHOUT_LIMIT = "без обмеження";
        private static final String WITH_LIMIT = "з обмеженням";

        private void sendButtonsForMenu4() {
            List<List<String>> buttons = new ArrayList<>();
            List<String> firstRow = new ArrayList<>();
            firstRow.add(WITHOUT_LIMIT);
            firstRow.add(WITH_LIMIT);
            buttons.add(firstRow);
            TextMessage buttonsForMenu1 = new TextMessage("Обмеження по часу?(1 питання 1 хвилина)", buttons);
            sender.execute(buttonsForMenu1);
        }

        private void menu4(String userAnswer) {
            switch (userAnswer) {
                case WITH_LIMIT:
                    isWithTime = true;
                case WITHOUT_LIMIT:
                    sendButtonsForAreYouReady();
                    currentMenu = Menu.ARE_YOU_READY;
                    break;
                default:
                    //#TODO
                    break;
            }
        }
    }

    private class AreYouReadyMenu {
        private static final String YES = "yes";
        private static final String NOPE = "nope";

        private void sendButtonsForAreYouReady() {
            List<List<String>> buttons = new ArrayList<>();
            List<String> firstRow = new ArrayList<>();
            firstRow.add(YES);
            firstRow.add(NOPE);
            buttons.add(firstRow);
            TextMessage buttonsForMenu1 = new TextMessage("Are you fucking ready?", buttons);
            sender.execute(buttonsForMenu1);
        }

        private void areYouReady(String userAnswer) {
            switch (userAnswer) {
                case YES:
                    createQuiz();
                    quiz.sendFirstQuestion();
                    break;
                case NOPE:
                    sendButtonsForAreYouReady();
                    sender.sendText("не правильна відповідь,спробуй ще раз");
                    break;
                default:
                    //#TODO
                    break;
            }
        }
    }
}
