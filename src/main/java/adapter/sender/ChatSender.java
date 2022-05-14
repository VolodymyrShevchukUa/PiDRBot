package adapter.sender;

import adapter.message.MessageI;
import adapter.message.TextMessage;
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
        return execute(new TextMessage(text));
    }

    @Override
    public Message execute(MessageI messageI) {
        messageI.setChatId(chatID + "");
        return sender.execute(messageI);
    }

}
