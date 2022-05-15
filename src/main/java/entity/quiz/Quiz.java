package entity.quiz;

import adapter.sender.ChatSenderI;
import entity.Question;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public abstract class Quiz {
    private final MessageIdCheck checker = new MessageIdCheck();
    private final Queue<Question> queueOfQuestion;
    private final ChatSenderI sender;

    Quiz(Queue<Question> queueOfQuestion, ChatSenderI sender) {
        this.queueOfQuestion = queueOfQuestion;
        this.sender = sender;
    }

    protected abstract void processAnswer(CallbackQuery callbackQuery);
    protected abstract String getResult();

    public boolean isEnd() {
        return queueOfQuestion.isEmpty();
    }

    public void processCallbackQuery(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        int messageId = message.getMessageId();
        if (checker.isUnic(messageId)) {
            checker.registrateProcessedMessageId(messageId);
            processAnswer(callbackQuery);
            if (isEnd()) {
                sendResult();
            } else {
                Question question = queueOfQuestion.poll();
                Message execute = sender.execute(question.createMessage());
                checker.registrateNewMessageId(execute.getMessageId());
            }
        }
    }


    public void sendFirstQuestion() {
        Question question = queueOfQuestion.poll();
        Message execute = sender.execute(question.createMessage());
        checker.registrateNewMessageId(execute.getMessageId());
    }

    public void sendResult() {
        sender.sendText(getResult());
    }
    protected void sendTextMessage(String text){
        sender.sendText(text);
    }

    private static class MessageIdCheck {
        private final Map<Integer, Boolean> map = new HashMap<>();

        private void registrateNewMessageId(int id) {
            map.put(id, Boolean.FALSE);
        }

        private void registrateProcessedMessageId(int id) {
            map.put(id, Boolean.TRUE);
        }

        private boolean isUnic(int id) {
            return Boolean.FALSE.equals(map.get(id));
        }
    }
}
