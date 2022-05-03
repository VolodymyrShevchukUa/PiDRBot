package handlers;

import entity.Ticket;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Set;

public class TestStrategy implements Strategy {
    private int countAnser;
    private final Set<Integer> messenger = new HashSet<>(); // Херня для перевірки на кількість натискань
    private final AbsSender sender;
    private final Ticket currentTicket;

    public TestStrategy(Ticket currentTicket, AbsSender sender) {
        this.sender = sender;
        this.currentTicket = currentTicket;
    }

    private void validateAnswer(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("true")) {
            countAnser += 5;
        }
    }

    @Override
    public Strategy getStrategy() {
        return currentTicket.isEnd() ? new MainMenuStrategy(sender) : this;
    }

    @Override
    public void onUpdateReceived(Update update) throws TelegramApiException {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        long chatID = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        if (!messenger.contains(messageId)) {
            validateAnswer(callbackQuery);
            messenger.add(messageId);
            if (currentTicket.isEnd()) {
                sender.execute(new SendMessage().builder().chatId(chatID + "").text(countAnser + "%").build());
            } else {
                sender.execute(currentTicket.getNextSendPhoto(chatID));
            }
        }
    }
}
