package handlers;

import adapter.sender.ChatSenderI;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class NavigationButtons implements Strategy {

    private static final String TO_PREVIOUS_MENU = "назад";
    private static final String TO_MAIN_MENU = "до головного меню";
    protected static final List<String> toPreviousStAndAMinMenuCommands = Collections.unmodifiableList(createListOfCommands());
    private final Strategy previousSt;
    private Strategy nextSt = this;

    protected final ChatSenderI sender;

    public void setNextSt(Strategy nextSt) {
        this.nextSt = nextSt;
    }

    private static List<String> createListOfCommands() {
        List<String> row = new ArrayList<>();
        row.add(TO_PREVIOUS_MENU);
        row.add(TO_MAIN_MENU);
        return row;
    }

    protected NavigationButtons(NavigationButtons previousSt) {
        this.previousSt = previousSt;
        this.sender = previousSt.sender;
    }

    protected NavigationButtons(Strategy previousSt, ChatSenderI sender) {
        this.previousSt = previousSt;
        this.sender = sender;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userAnswer = update.getMessage().getText();
        if (!processMyCommand(userAnswer)) {
            processUserAnswer(update);
        }
        nextSt.sendCommands();
    }

    protected abstract void processUserAnswer(Update update);

    private boolean processMyCommand(String userAnswer) {
        boolean isSuccess = true;
        switch (userAnswer) {
            case TO_PREVIOUS_MENU:
                nextSt = previousSt;
                break;
            case TO_MAIN_MENU:
                nextSt = new MainMenuStrategy(sender);
                break;
            default:
                isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public Strategy getStrategy() {
        return nextSt;
    }

}
