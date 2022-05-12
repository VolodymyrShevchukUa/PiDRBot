package adapter.sender;


import adapter.message.MessageI;
import adapter.message.TextMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class SenderTelegrambots extends TelegramLongPollingBot implements Sender {
    // TODO:Add a logger
    @Override
    public Message execute(MessageI message) {
        try {
            return tryToSend(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return new Message();
        }
    }

    private Message tryToSend(MessageI message) throws TelegramApiException {
        if (message instanceof SendMessage sendMessage) {
             return execute(sendMessage);
        } else if (message instanceof SendPhoto photoMessage) {
            return execute(photoMessage);
        } else {
            throw new UnsupportedOperationException("not implemented yet");
        }
    }

    @Override
    public Message sendText(long chatID, String text) {
// Каст?
        return execute((MessageI) new TextMessage(chatID,text));
    }
}
