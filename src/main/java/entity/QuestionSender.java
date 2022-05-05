package entity;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Треба дунути
public class QuestionSender {
    private final AbsSender sender;
    private final long chatId;
    private final Ticket ticket;

    public QuestionSender(AbsSender sender, long chatId, Ticket ticket) {
        this.sender = sender;
        this.chatId = chatId;
        this.ticket = ticket;
    }


    public void sendNextQuestion() throws TelegramApiException {
        Question question = ticket.getNextQuestion();
        if(question.hasPhoto()){
            try {
            sender.execute(getNextSendPhoto(question));}
            catch (Exception e){
                System.out.println(question.getUrl());
            }

        }else{
            sender.execute(getNextSendMessage(question));
        }

    }
    public boolean isEnd(){
        return ticket.isEnd();
    }

    private SendMessage getNextSendMessage(Question question) {
        InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardButtonList(question.getCountOfButton());
        inlineKeyboardMarkup.getKeyboard().get(0).get(question.getCorrectButon()).setCallbackData("true");

        return (new SendMessage().builder()
                .replyMarkup(inlineKeyboardMarkup)
                .text(question.getCaption())
                .chatId(chatId + "")
                .build());
    }

    private SendPhoto getNextSendPhoto(Question question) {
        InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardButtonList(question.getCountOfButton());
        inlineKeyboardMarkup.getKeyboard().get(0).get(question.getCorrectButon()).setCallbackData("true");

        return (new SendPhoto().builder()
                .replyMarkup(inlineKeyboardMarkup)
                .photo(new InputFile(question.getUrl()))
                .caption(question.getCaption())
                .chatId(chatId + "")
                .build());
    }

    private InlineKeyboardMarkup createInlineKeyboardButtonList(int countOfButton) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        for (Integer i = 1; i < countOfButton; i++) {
            buttons.add(new InlineKeyboardButton().builder()
                    .callbackData("false")
                    .text(i.toString() + "✅")
                    .build());
        }
        in.setKeyboard(Collections.singletonList((buttons)));
        return in;
    }

}
