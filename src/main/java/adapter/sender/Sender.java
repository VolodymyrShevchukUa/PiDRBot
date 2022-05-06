package adapter.sender;

import adapter.message.Message;

public interface Sender {
    void send(Message message);
}
