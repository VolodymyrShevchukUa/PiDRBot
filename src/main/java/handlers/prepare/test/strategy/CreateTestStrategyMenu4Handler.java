package handlers.prepare.test.strategy;

import adapter.message.TextMessage;
import handlers.NavigationButtons;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateTestStrategyMenu4Handler extends NavigationButtons {

    private static final String WITHOUT_LIMIT = "без обмеження";
    private static final String WITH_LIMIT = "з обмеженням";
    private static final List<List<String>> listOfCommands = Collections.unmodifiableList(createListOfCommands());

    private final QuizBuilder quizBuilder;

    private static List<List<String>> createListOfCommands() {
        List<List<String>> buttons = new ArrayList<>();
        List<String> firstRow = new ArrayList<>();
        firstRow.add(WITHOUT_LIMIT);
        firstRow.add(WITH_LIMIT);
        buttons.add(firstRow);
        buttons.add(toPreviousStAndAMinMenuCommands);
        return buttons;
    }

    @Override
    public void sendCommands() {
        sender.execute(new TextMessage("Обмеження по часу?(1 питання 1 хвилина)", listOfCommands));
    }

    protected CreateTestStrategyMenu4Handler(NavigationButtons previousSt, QuizBuilder quizBuilder) {
        super(previousSt);
        this.quizBuilder = quizBuilder;
    }

    @Override
    public void processUserAnswer(Update update) {
        String userAnswer = update.getMessage().getText();
        switch (userAnswer) {
            case WITH_LIMIT:
                quizBuilder.setWithTime(true);
                setNextSt(new CreateTestStrategyMenuEndHandler(this, quizBuilder));
                break;
            case WITHOUT_LIMIT:
                setNextSt(new CreateTestStrategyMenuEndHandler(this,  quizBuilder));
                break;
            default:
                break;
        }
    }

}
