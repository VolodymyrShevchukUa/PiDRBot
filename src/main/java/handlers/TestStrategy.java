package handlers;

import adapter.message.MessageI;
import adapter.message.TextMessage;
import adapter.sender.Sender;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class TestStrategy implements Strategy {
    private int rightAnswer;
    private final MessageIdCheck checker = new MessageIdCheck();
    private final Queue<MessageI> queueOfTicketMessages;
    private final Sender sender;
    private Strategy strategy = this;

    public TestStrategy(Queue<MessageI> queueOfTicketMessages, Sender sender) {
        this.queueOfTicketMessages = queueOfTicketMessages;
        this.sender = sender;
    }

    private void validateAnswer(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("true")) {
            rightAnswer += 5;
        }
    }

    @Override
    public Strategy getStrategy() {
        return strategy;
    }

    private void processMessage() {

    }

    @Override
    public void onUpdateReceived(Update update) throws TelegramApiException {
        if (update.hasCallbackQuery()) {
            processCallbackQuery(update.getCallbackQuery());
        } else {
            processMessage();
        }
    }

    private void processCallbackQuery(CallbackQuery callbackQuery) {
        int messageId = callbackQuery.getMessage().getMessageId();
        if (checker.isUnic(messageId)) {
            validateAnswer(callbackQuery);
            MessageI messageI = queueOfTicketMessages.poll();
            if (messageI == null) {
                goToMainMenu();
                messageI = new TextMessage(callbackQuery.getMessage().getChatId(), rightAnswer + "%");
            }
            Message execute = sender.execute(messageI);
            checker.registrateMessageId(execute.getMessageId());
        }
    }

    public void sendFirstQuestion() {
        checker.registrateMessageId(sender.execute(queueOfTicketMessages.poll()).getMessageId());
    }

    private void goToMainMenu() {
        strategy = new MainMenuStrategy(sender);
    }

    private static class MessageIdCheck {
        private final Map<Integer, Boolean> map = new HashMap<>();

        private void registrateMessageId(int id) {
            map.put(id, false);
        }

        private boolean isUnic(int id) {
            Boolean key = map.get(id);
            if (key != null && !key) {
                map.put(id, true);
                return true;
            }
            return false;
        }
    }
}

