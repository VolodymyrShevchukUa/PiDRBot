package handlers;

import adapter.sender.Sender;
import entity.Question;
import entity.Ticket;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import utils.json.JSONToQuestion;
import utils.json.QuestionToJSON;

import java.util.List;

public class MainMenuStrategy implements Strategy {
    private final static List<Question> questionsList = new JSONToQuestion(QuestionToJSON.PATH).getQuestions();
    private final static int COUNT_QUESTION = 1;
    private final Sender sender;//PDRBot
    private Strategy nextStrategy = this;

    public MainMenuStrategy(Sender sender) {
        this.sender = sender;
    }

    @Override
    public Strategy getStrategy() {
        return nextStrategy;
    }

    @Override
    public void onUpdateReceived(Update update) throws TelegramApiException {
        Message message = update.getMessage();
        long chatID = message.getChatId();
        if (message.getText().startsWith("/")) {
            switch (message.getText()) {
                case "/test":
                    sender.sendText(chatID, "Lets start");
                    TestStrategy testStrategy = new TestStrategy(new Ticket(questionsList, COUNT_QUESTION).getQueueOfTicketMessages(chatID), sender);
                    testStrategy.sendFirstQuestion();
                    nextStrategy = testStrategy;
                    break;
                case "/rank":
                    sender.sendText(chatID, "Gomos");
                    break;
                default:
                    sender.sendText(chatID, "unknown Commmmmmand");
                    break;
            }
        }

    }
}
