package entity.quiz;

import adapter.message.MessageI;
import adapter.sender.ChatSenderI;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class QuizWithTime extends QuizWithMarks {
    private long startTime;
    private long currentTime;
    private final long  totalTime;


    public QuizWithTime(Queue<MessageI> queueOfTicketMessages, ChatSenderI sender) {
        this(queueOfTicketMessages, sender, queueOfTicketMessages.size());
    }

    public QuizWithTime(Queue<MessageI> queueOfTicketMessages, ChatSenderI sender, int numberOfAttempts) {
        super(queueOfTicketMessages, sender, numberOfAttempts);
        totalTime = TimeUnit.MINUTES.toMillis(queueOfTicketMessages.size());
    }

    @Override
    public void sendFirstQuestion() {
        startTime = System.currentTimeMillis();
        super.sendFirstQuestion();
    }

    @Override
    public boolean isEnd() {
        return super.isEnd() || isTimeOut();
    }

    @Override
    protected void processAnswer(CallbackQuery callbackQuery) {
        currentTime = System.currentTimeMillis();
        if (!isTimeOut()) {
            super.processAnswer(callbackQuery);
            if(!super.isEnd()){
                long minutes = TimeUnit.MILLISECONDS.toMinutes( totalTime+(startTime-currentTime))+1;
                sendTextMessage("Вам лишилось " + minutes + " хв.");
            }
        }else{
            sendTextMessage("Відповідь не враховано, час вийшов");
        }
    }

    public boolean isTimeOut() {
        return (currentTime - startTime) > totalTime;
    }
}
