package handlers;

import adapter.sender.Sender;
import entity.Question;
import entity.Ticket;
import handlers.test.Quiz;
import handlers.test.TestStrategy;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import utils.json.JSONToQuestion;
import utils.json.QuestionToJSON;

import java.util.List;

public class MainMenuStrategy implements Strategy {
    private static final List<Question> questionsList = new JSONToQuestion(QuestionToJSON.PATH).getQuestions();
    private static final int COUNT_QUESTION = 1;
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
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        long chatID = message.getChatId();
        if (message.getText().startsWith("/")) {
            switch (message.getText()) {
                case "/test":
                    sender.sendText(chatID, "Lets start");
                    Quiz quiz = new Quiz(new Ticket(questionsList, COUNT_QUESTION).getQueueOfTicketMessages(chatID), sender);
                    quiz.sendFirstQuestion();
                    nextStrategy = new TestStrategy(quiz, sender);
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
