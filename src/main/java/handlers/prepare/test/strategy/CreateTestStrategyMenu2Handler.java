package handlers.prepare.test.strategy;

import adapter.sender.ChatSenderI;
import handlers.NavigationButtons;
import handlers.Strategy;
import org.telegram.telegrambots.meta.api.objects.Update;
import utils.QuestionCache;

import java.util.Collections;
import java.util.List;

public class CreateTestStrategyMenu2Handler extends NavigationButtons {

    private static final List<List<String>> listOfCommands = Collections.unmodifiableList(createListOfCommands());
    private final QuizBuilder quizBuilder;

    private static List<List<String>> createListOfCommands() {
        //#todo
        return null;
    }

    @Override
    public void sendCommands() {
        sender.sendText("зроби нормальні кнопки 2");
    }

    protected CreateTestStrategyMenu2Handler(Strategy previousSt, ChatSenderI sender, QuizBuilder quizBuilder) {
        super(previousSt, sender);
        this.quizBuilder = quizBuilder;
    }

    @Override
    public void processUserAnswer(Update update) {
        String userAnswer = update.getMessage().getText();
        if (QuestionCache.SET_OF_SUBJECT.contains(userAnswer)) {
            quizBuilder.setNameOfSubject(userAnswer);
            setNextSt(new CreateTestStrategyMenu3Handler(this, sender, quizBuilder));
        }
    }
}
