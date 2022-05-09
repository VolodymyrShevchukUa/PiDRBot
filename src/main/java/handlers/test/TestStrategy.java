package handlers.test;

import adapter.sender.Sender;
import handlers.MainMenuStrategy;
import handlers.Strategy;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestStrategy implements Strategy {
    private final Sender sender;
    private final Quiz quiz;
    private Strategy strategy = this;

    public TestStrategy(Quiz quiz, Sender sender) {
        this.sender = sender;
        this.quiz = quiz;
    }

    @Override
    public Strategy getStrategy() {
        return strategy;
    }

    private void processMessage() {

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            processCallbackQuery(update.getCallbackQuery());
        } else {
            processMessage();
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

