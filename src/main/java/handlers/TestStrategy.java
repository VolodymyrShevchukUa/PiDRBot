package handlers;

import entity.QuestionSender;
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
    private final QuestionSender questionSender;

    public TestStrategy(AbsSender sender, QuestionSender questionSender) {
        this.sender = sender;
        this.questionSender = questionSender;
    }

    private void validateAnswer(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("true")) {
            countAnser += 5;
        }
    }

    @Override
    public Strategy getStrategy() {
        return questionSender.isEnd() ? new MainMenuStrategy(sender) : this;
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

    private void processCallbackQuery(CallbackQuery callbackQuery) throws TelegramApiException {
        long chatID = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        if (!messenger.contains(messageId)) {
            validateAnswer(callbackQuery);
            messenger.add(messageId);
            if (questionSender.isEnd()) {
                sender.execute(new SendMessage().builder().chatId(chatID + "").text(countAnser + "%").build());
            } else {
                questionSender.sendNextQuestion();
            }
        }
    }
}

