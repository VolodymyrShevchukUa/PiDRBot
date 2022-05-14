package adapter.sender;

import adapter.message.MessageI;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ChatSender implements ChatSenderI {
    private final SenderTelegrambots sender;
    private final long chatID;

    public ChatSender(SenderTelegrambots sender, long chatID) {
        this.sender = sender;
        this.chatID = chatID;
    }

    @Override
    public Message sendText(String text) {
        return sender.sendText(chatID, text);
    }

    @Override
    public Message execute(MessageI messageI) {
        return sender.execute(messageI);
    }

}
