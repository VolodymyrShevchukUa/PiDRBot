package v2.models;

import adapter.message.EditMessageReplyMarkupMessage;
import adapter.message.updaters.ButtonMessageUpdater;
import adapter.sender.ChatSenderI;
import entity.Question;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import v2.entity.Quiz;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class QuizModel {
    private final QuizModel.MessageIdCheck checker = new QuizModel.MessageIdCheck();
    private final Queue<Question> queueOfQuestion;
    private final ChatSenderI sender;

    QuizModel(Queue<Question> queueOfQuestion, ChatSenderI sender) {
        this.queueOfQuestion = queueOfQuestion;
        this.sender = sender;
    }

    protected void processAnswer(CallbackQuery callbackQuery) {
        updateLastMessage(callbackQuery.getMessage());
    }

    private void updateLastMessage(Message message) {
        EditMessageReplyMarkupMessage editMessageReplyMarkupMessage = new ButtonMessageUpdater(message)
                .showAnswer();
        sender.execute(editMessageReplyMarkupMessage);
    }

    protected abstract String getResult();

    public boolean isEnd() {
        return queueOfQuestion.isEmpty();
    }

    public void processCallbackQuery(CallbackQuery callbackQuery, Quiz quiz) {
        Message message = callbackQuery.getMessage();
        int messageId = message.getMessageId();
        if (checker.isUnic(messageId)) {
            checker.registrateProcessedMessageId(messageId);
            processAnswer(callbackQuery);
            if (isEnd()) {
                sendResult();
            } else {
                sendNextQuestion();
            }
        }
    }

    private void sendNextQuestion() {
        checker.registrateNewMessageId(sender.execute( Objects.requireNonNull(queueOfQuestion.poll()).createMessage()).getMessageId());
    }

    public void sendFirstQuestion() {
        sendNextQuestion();
    }

    public void sendResult() {
        sender.sendText(getResult());
    }

    protected void sendTextMessage(String text) {
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
