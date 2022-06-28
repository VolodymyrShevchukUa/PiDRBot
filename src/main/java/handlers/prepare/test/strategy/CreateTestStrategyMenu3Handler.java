package handlers.prepare.test.strategy;

import adapter.message.TextMessage;
import adapter.sender.ChatSenderI;
import handlers.NavigationButtons;
import handlers.Strategy;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateTestStrategyMenu3Handler extends NavigationButtons {

    private static final List<List<String>> listOfCommands = Collections.unmodifiableList(createListOfCommands());

    private final QuizBuilder quizBuilder;

    private static List<List<String>> createListOfCommands() {
        List<List<String>> buttons = new ArrayList<>();
        List<String> firstRow = new ArrayList<>();
        firstRow.add("10");
        firstRow.add("20");
        List<String> secondRow = new ArrayList<>();
        secondRow.add("30");
        secondRow.add("40");
        buttons.add(firstRow);
        buttons.add(secondRow);
        buttons.add(toPreviousStAndAMinMenuCommands);
        return buttons;
    }

    @Override
    public void sendCommands() {
        sender.execute(new TextMessage("Оберіть кількість питань", listOfCommands));
    }

    protected CreateTestStrategyMenu3Handler(Strategy previousSt, ChatSenderI sender, QuizBuilder quizBuilder) {
        super(previousSt, sender);
        this.quizBuilder = quizBuilder;
    }

    @Override
    public void processUserAnswer(Update update) {
        String userAnswer = update.getMessage().getText();
        try {
            int countOfQuestion = Integer.parseInt(userAnswer);
            if (countOfQuestion == 10 || countOfQuestion == 20 || countOfQuestion == 30 || countOfQuestion == 40) {
                quizBuilder.setCountOfQuestion(countOfQuestion);
                setNextSt(new CreateTestStrategyMenu4Handler(this, sender, quizBuilder));
            } else {
                sender.execute(new TextMessage("Ви ввели не вірне число"));
            }
        } catch (NumberFormatException e) {
            sender.execute(new TextMessage("Ви ввели не вірне число"));
        }
    }
}
