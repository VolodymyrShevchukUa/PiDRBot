package handlers.test;

import adapter.message.MessageI;
import adapter.sender.Sender;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Queue;

public class QuizWithMarks extends Quiz {
    private float rightAnswer = 0;
    private final int countOfQuestion;
    private final int numberOfAttempts;

    public QuizWithMarks(Queue<MessageI> queueOfTicketMessages, Sender sender) {
        super(queueOfTicketMessages, sender);
        countOfQuestion = queueOfTicketMessages.size();
        numberOfAttempts = countOfQuestion;
    }

    public QuizWithMarks(Queue<MessageI> queueOfTicketMessages, Sender sender, int numberOfAttempts) {
        super(queueOfTicketMessages, sender);
        countOfQuestion = queueOfTicketMessages.size();
        this.numberOfAttempts = numberOfAttempts;
    }

    @Override
    void processAnswer(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("true")) {
            float ra = 100f / countOfQuestion;
            rightAnswer += ra;
        } else {

        }
    }

    @Override
    String getResult() {
        return String.format("%.2f", rightAnswer) + "%";
    }

}
