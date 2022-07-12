package v2.models;

import entity.quiz.Quiz;
import v2.controllers.Controller;
import v2.controllers.MainMenuController;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestModel implements Model {

    private final Controller father;
    public TestModel(Controller father) {
        this.father = father;
    }

    public Controller processMessage(Update update, Quiz quiz) {
        String userAnswer = update.getMessage().getText();
        if (userAnswer.equals("/stop")) {
            quiz.sendResult();
            return new MainMenuController();
        }
        return father;
    }

    public Controller processCallbackQuery(CallbackQuery callbackQuery, Quiz quiz) {
        quiz.processCallbackQuery(callbackQuery);
        if (quiz.isEnd()) {
            return new MainMenuController();
        }
        return father;
    }
}
