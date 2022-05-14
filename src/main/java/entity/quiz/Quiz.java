package entity.quiz;

import adapter.message.MessageI;
import adapter.message.TextMessage;
import adapter.sender.Sender;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public abstract class Quiz {
    private final MessageIdCheck checker = new MessageIdCheck();
    private final Queue<MessageI> queueOfTicketMessages;
    private final Sender sender;

    Quiz(Queue<MessageI> queueOfTicketMessages, Sender sender) {
        this.queueOfTicketMessages = queueOfTicketMessages;
        this.sender = sender;
    }

    protected abstract void processAnswer(CallbackQuery callbackQuery);
    protected abstract String getResult();

    public boolean isEnd() {
        return queueOfTicketMessages.isEmpty();
    }

    public void processCallbackQuery(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        int messageId = message.getMessageId();
        if (checker.isUnic(messageId)) {
            checker.registrateProcessedMessageId(messageId);
            processAnswer(callbackQuery);
            if (isEnd()) {
                sendResult(message.getChatId());
            } else {
                Message execute = sender.execute(queueOfTicketMessages.poll());
                checker.registrateNewMessageId(execute.getMessageId());
            }
        }
    }


    public void sendFirstQuestion() {
        checker.registrateNewMessageId(sender.execute(queueOfTicketMessages.poll()).getMessageId());
    }

    public void sendResult(long chatID) {
        sender.sendText(chatID,getResult());
    }
    protected void sendTextMessage(long chatId,String text){
        sender.sendText(chatId,text);
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
