package handlers;

import adapter.sender.ChatSenderI;
import entity.quiz.Quiz;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestStrategy implements Strategy {
    private final ChatSenderI sender;
    private final Quiz quiz;
    private Strategy strategy = this;

    public TestStrategy(ChatSenderI sender, Quiz quiz) {
        this.sender = sender;
        this.quiz = quiz;
    }

    @Override
    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public void sendCommands() {
        //#TODO
        quiz.sendFirstQuestion();
    }

    private void processMessage(Update update) {
        String userAnswer = update.getMessage().getText();
        if (userAnswer.equals("/stop")) {
            goToMainMenu();
            quiz.sendResult();
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

