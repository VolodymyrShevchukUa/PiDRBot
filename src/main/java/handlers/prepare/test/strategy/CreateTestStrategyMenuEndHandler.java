package handlers.prepare.test.strategy;

import adapter.message.TextMessage;
import handlers.NavigationButtons;
import handlers.TestStrategy;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateTestStrategyMenuEndHandler extends NavigationButtons {
    private static final String YES = "yes";
    private static final String NOPE = "nope";
    private static final List<List<String>> listOfCommands = Collections.unmodifiableList(createListOfCommands());

    private final QuizBuilder quizBuilder;

    private static List<List<String>> createListOfCommands() {
        List<List<String>> buttons = new ArrayList<>();
        List<String> firstRow = new ArrayList<>();
        firstRow.add(YES);
        firstRow.add(NOPE);
        buttons.add(firstRow);
        buttons.add(toPreviousStAndAMinMenuCommands);
        return buttons;
    }

    @Override
    public void sendCommands() {
        sender.execute(new TextMessage("Are you fucking ready?", listOfCommands));
    }

    protected CreateTestStrategyMenuEndHandler(NavigationButtons previousSt, QuizBuilder quizBuilder) {
        super(previousSt);
        this.quizBuilder = quizBuilder;
    }

    @Override
    public void processUserAnswer(Update update) {
        String userAnswer = update.getMessage().getText();
        switch (userAnswer) {
            case YES:
                setNextSt(new TestStrategy(sender, quizBuilder.build(sender)));
                break;
            case NOPE:
                sender.sendText("не правильна відповідь,спробуй ще раз");
                break;
            default:
                break;

        }
    }
}
