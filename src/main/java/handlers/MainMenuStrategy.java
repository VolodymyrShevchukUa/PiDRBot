package handlers;

import entity.Question;
import entity.QuestionSender;
import entity.Ticket;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import utils.json.JSONToQuestion;
import utils.json.QuestionToJSON;

import java.util.List;

public class MainMenuStrategy implements Strategy {
    private final static List<Question> questionsList = new JSONToQuestion(QuestionToJSON.PATH).getQuestions();
    private final static int COUNT_QUESTION = 20;
    private final AbsSender sender;//PDRBot
    private Strategy nextStrategy = this;

    public MainMenuStrategy(AbsSender sender) {
        this.sender = sender;

    }
    private SendMessage createTextMessage(Update update,String text){
        Message message = update.getMessage();
        SendMessage command = new SendMessage();
        command.setChatId(message.getChatId() + "");
        command.setText(text);
        return command;
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
                    sender.execute(createTextMessage(update,"Lets start"));
                    Ticket currentTicket = new Ticket(questionsList, COUNT_QUESTION);
                    QuestionSender questionSender = new QuestionSender(sender,chatID,currentTicket);
                    questionSender.sendNextQuestion();
                    nextStrategy = new TestStrategy( sender, questionSender);
                    break;
                case "/rank":
                    sender.execute(createTextMessage(update,"Gomos"));
                    break;
                default:
                    sender.execute(createTextMessage(update,"unknown Commmmmmand"));
                    break;
            }
        }

    }
}
