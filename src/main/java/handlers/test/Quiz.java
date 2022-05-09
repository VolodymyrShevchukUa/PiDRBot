package handlers.test;

import adapter.message.MessageI;
import adapter.message.TextMessage;
import adapter.sender.Sender;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Quiz {
    private int rightAnswer = 0;
    private final MessageIdCheck checker = new MessageIdCheck();
    private final Queue<MessageI> queueOfTicketMessages;
    private final Sender sender;
    private boolean isEnd = false;

    public Quiz(Queue<MessageI> queueOfTicketMessages, Sender sender) {
        this.queueOfTicketMessages = queueOfTicketMessages;
        this.sender = sender;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void processCallbackQuery(CallbackQuery callbackQuery) {
        int messageId = callbackQuery.getMessage().getMessageId();
        if (checker.isUnic(messageId)) {
            checker.registrateProcessedMessageId(messageId);
            validateAnswer(callbackQuery);
            MessageI messageI = queueOfTicketMessages.poll();
            if (messageI == null) {
                isEnd = true;
                messageI = new TextMessage(callbackQuery.getMessage().getChatId(), rightAnswer + "%");
            }
            Message execute = sender.execute(messageI);
            checker.registrateNewMessageId(execute.getMessageId());
        }
    }

    private void validateAnswer(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("true")) {
            rightAnswer += 5;
        }
    }

    public void sendFirstQuestion() {
        checker.registrateNewMessageId(sender.execute(queueOfTicketMessages.poll()).getMessageId());
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
