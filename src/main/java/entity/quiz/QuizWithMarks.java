package entity.quiz;

import adapter.message.MessageI;
import adapter.sender.Sender;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Queue;

public class QuizWithMarks extends Quiz {
    private float rightAnswer = 0;
    private final int countOfQuestion;
    private int numberOfAttempts;//

    public QuizWithMarks(Queue<MessageI> queueOfTicketMessages, Sender sender) {
        super(queueOfTicketMessages, sender);
        countOfQuestion = queueOfTicketMessages.size();
        numberOfAttempts = countOfQuestion;
    }

    public QuizWithMarks(Queue<MessageI> queueOfTicketMessages, Sender sender, int numberOfAttempts) {
        this(queueOfTicketMessages,sender);
        this.numberOfAttempts = numberOfAttempts;
    }

    @Override
    public boolean isEnd() {
        return super.isEnd()||numberOfAttempts==0;
    }

    @Override
    protected void processAnswer(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("true")) {
            sendTextMessage(callbackQuery.getMessage().getChatId(),"✅ Відповідь правильна ✅");
            float ra = 100f / countOfQuestion;
            rightAnswer += ra;
        } else {
            sendTextMessage(callbackQuery.getMessage().getChatId()," Відповідь хуйова ,правильна відповідь - доступна по преміум підписці");
            numberOfAttempts--;
            if (numberOfAttempts==0){
                sendTextMessage(callbackQuery.getMessage().getChatId(),"Відповідь не правильна, спроби вичерпано");
            }

        }
    }

    @Override
    protected String getResult() {
        return String.format("%.2f", rightAnswer) + "%";
    }

}
