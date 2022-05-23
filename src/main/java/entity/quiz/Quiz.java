package entity.quiz;

import adapter.message.EditMessageReplyMarkupMessage;
import adapter.message.updaters.ButtonMessageUpdater;
import adapter.sender.ChatSenderI;
import entity.Question;
import entity.QuestionAnswer;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public abstract class Quiz {
    private final MessageIdCheck checker = new MessageIdCheck();
    private final Queue<Question> queueOfQuestion;
    private final ChatSenderI sender;
    private QuestionAnswer userAnswer;

    Quiz(Queue<Question> queueOfQuestion, ChatSenderI sender) {
        this.queueOfQuestion = queueOfQuestion;
        this.sender = sender;
    }

    protected void processAnswer(CallbackQuery callbackQuery) {
        userAnswer = QuestionAnswer.parseCallbackQueryData(callbackQuery.getData());
        updateLastMessage(callbackQuery.getMessage());
    }

    private void updateLastMessage(Message message) {
        String text = userAnswer.isCurrentQuestionTrue() ? "true" : "false";
        EditMessageReplyMarkupMessage editMessageReplyMarkupMessage = new ButtonMessageUpdater(message)
                .addTextToButton(0, userAnswer.getIndexOfUsedButton() - 1, text);
        sender.execute(editMessageReplyMarkupMessage);
    }

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
                sendNextQuestion();
            }
        }
    }

    private void sendNextQuestion() {
        Question currentQuestion = queueOfQuestion.poll();
        Message execute = sender.execute(currentQuestion.createMessage());
        checker.registrateNewMessageId(execute.getMessageId());
    }

    public void sendFirstQuestion() {
        sendNextQuestion();
    }

    public void sendResult() {
        sender.sendText(getResult());
    }
    protected void sendTextMessage(String text){
        sender.sendText(text);
    }

    protected boolean isAnswerTrue() {
        return userAnswer.isCurrentQuestionTrue();
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
