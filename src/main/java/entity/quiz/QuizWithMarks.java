package entity.quiz;

import adapter.sender.ChatSenderI;
import entity.Question;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Queue;

public class QuizWithMarks extends Quiz {
    private float rightAnswer = 0;
    private final int countOfQuestion;
    private int numberOfAttempts;

    public QuizWithMarks(Queue<Question> queueOfQuestion, ChatSenderI sender, int numberOfAttempts) {
        super(queueOfQuestion, sender);
        this.countOfQuestion = queueOfQuestion.size();
        this.numberOfAttempts = numberOfAttempts;
    }

    @Override
    public boolean isEnd() {
        return super.isEnd() || numberOfAttempts == 0;
    }

    @Override
    protected void processAnswer(CallbackQuery callbackQuery) {
        super.processAnswer(callbackQuery);
        if (Boolean.parseBoolean(callbackQuery.getData())) {
            sendTextMessage("✅ Відповідь правильна ✅");
            float ra = 100f / countOfQuestion;
            rightAnswer += ra;
        } else {
            sendTextMessage(" Відповідь хуйова ,правильна відповідь - доступна по преміум підписці");
            numberOfAttempts--;
            if (numberOfAttempts == 0) {
                sendTextMessage("Відповідь не правильна, спроби вичерпано");
            }
        }
    }

    @Override
    protected String getResult() {
        return String.format("%.2f", rightAnswer) + "%";
    }

}
