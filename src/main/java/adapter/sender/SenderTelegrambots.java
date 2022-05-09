package adapter.sender;


import adapter.message.Message;
import adapter.message.PhotoMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class SenderTelegrambots extends TelegramLongPollingBot implements Sender {
    // TODO:Add a logger
    @Override
    public void send(Message message) {
        try {
            tryToSend(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void tryToSend(Message message) throws TelegramApiException {
        if (message instanceof SendMessage) {
            execute((SendMessage) message);
        } else if (message instanceof PhotoMessage) {
            execute((PhotoMessage) message);
        } else {
            throw new UnsupportedOperationException("not implemented yet");
        }
    }
}
