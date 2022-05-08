package adapter.sender;

import adapter.message.MessageI;
import adapter.message.PhotoMessage;
import adapter.message.TextMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class SenderTelegrambots extends TelegramLongPollingBot implements Sender {
    @Override
    public Message sendText(long chatID, String text) {
        return execute((MessageI) new TextMessage(chatID, text));
    }

    @Override
    public Message execute(MessageI messageI) {
        try {
            return tryExecute(messageI);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public Message tryExecute(MessageI messageI) throws TelegramApiException {
        if (messageI instanceof SendPhoto photoMessage) {
            return execute(photoMessage);
        } else if (messageI instanceof SendMessage textMessage) {
            return execute(textMessage);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
