package handlers.prepare.test.strategy;

import adapter.message.TextMessage;
import adapter.sender.ChatSenderI;
import handlers.NavigationButtons;
import handlers.Strategy;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateTestStrategyMenu1Handler extends NavigationButtons {


    private static final String ALL_QUESTION = "всі питання";
    private static final String QUESTION_BY_SUBJECT = "питання по темам";
    private static final String REAL_TEST = "реальний тест";

    private static final List<List<String>> listOfCommands = Collections.unmodifiableList(createListOfCommands());

    private final QuizBuilder quizBuilder = new QuizBuilder();

    private static List<List<String>> createListOfCommands() {
        List<List<String>> buttons = new ArrayList<>();
        List<String> firstRow = new ArrayList<>();
        firstRow.add(ALL_QUESTION);
//            firstRow.add(QUESTION_BY_SUBJECT);
        List<String> secondRow = new ArrayList<>();
        secondRow.add(REAL_TEST);
        buttons.add(firstRow);
        buttons.add(secondRow);
        buttons.add(toPreviousStAndAMinMenuCommands);
        return buttons;
    }

    @Override
    public void sendCommands() {
        sender.execute(new TextMessage("Оберіть режим", listOfCommands));
    }

    public CreateTestStrategyMenu1Handler(Strategy previousSt, ChatSenderI sender) {
        super(previousSt, sender);
    }

    @Override
    public void processUserAnswer(Update update) {
        String userAnswer = update.getMessage().getText();
        switch (userAnswer) {
            case ALL_QUESTION:
                setNextSt(new CreateTestStrategyMenu3Handler(this, sender, quizBuilder));
                break;
            case QUESTION_BY_SUBJECT:
                setNextSt(new CreateTestStrategyMenu2Handler(this, sender, quizBuilder));
                break;
            case REAL_TEST:
                quizBuilder.setRealTest(true);
                quizBuilder.setWithTime(true);
                quizBuilder.setCountOfQuestion(20);
                setNextSt(new CreateTestStrategyMenuEndHandler(this, sender, quizBuilder));
                break;
            default:
                break;
        }
    }
}
