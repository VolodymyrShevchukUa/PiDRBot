package adapter.sender;


import adapter.message.MessageI;
import adapter.message.TextMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class SenderTelegrambots extends TelegramLongPollingBot {
    // TODO:Add a logger

    public Message execute(MessageI message) {
        try {
            return tryToSend(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return new Message();
        }
    }

    private Message tryToSend(MessageI message) throws TelegramApiException {
        if (message instanceof SendMessage) {
             return execute((SendMessage)message);
        } else if (message instanceof SendPhoto) {
            return execute((SendPhoto)message);
        } else {
            throw new UnsupportedOperationException("not implemented yet");
        }
    }
}
