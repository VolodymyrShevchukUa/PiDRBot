package handlers.prepare.test.strategy;

import adapter.message.TextMessage;
import adapter.sender.ChatSenderI;
import handlers.NavigationButtons;
import handlers.Strategy;
import org.telegram.telegrambots.meta.api.objects.Update;
import utils.QuestionCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateTestStrategyMenu2Handler extends NavigationButtons {

    private static final List<List<String>> listOfCommands = Collections.unmodifiableList(createListOfCommands());

    private final QuizBuilder quizBuilder;

    private static List<List<String>> createListOfCommands() {
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(NavigationButtons.toPreviousStAndAMinMenuCommands);
        return buttons;
    }

    @Override
    public void sendCommands() {
        sender.sendText("відправте номер теми");
        sender.execute(new TextMessage(QuestionCache.TEXT_VERSION_OF_LIST_THEME, listOfCommands));
    }

    protected CreateTestStrategyMenu2Handler(Strategy previousSt, ChatSenderI sender, QuizBuilder quizBuilder) {
        super(previousSt, sender);
        this.quizBuilder = quizBuilder;
    }

    @Override
    public void processUserAnswer(Update update) {
        String userAnswer = update.getMessage().getText();
        try {
            double d = Double.parseDouble(userAnswer);
            if (QuestionCache.validateThemeNumber(d)) {
                quizBuilder.setTheme(d);
                setNextSt(new CreateTestStrategyMenu4Handler(this, sender, quizBuilder));
            } else {
                sender.sendText("нісенітниця");
            }
        } catch (Exception ignored) {
            sender.sendText("невалідне число");
        }
    }
}
