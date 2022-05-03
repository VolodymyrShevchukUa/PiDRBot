package entity;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

public class Ticket {
    Integer captionText = 1;
    int currentQuestion = 0;
    // чи можемо ми засунути questions в конструктор
    private final List<Question> questions;

    public Ticket(List<Question> allQuestionsList, int countOfQuestions) {
        Set<Integer> ticketIndicator = new HashSet<>();
        this.questions = new ArrayList<>(countOfQuestions);
        int size = allQuestionsList.size();
        while (questions.size() < countOfQuestions) {
            int a = (int) (Math.random() * size);
            if (!ticketIndicator.contains(a)) {
                questions.add(allQuestionsList.get(a));
                ticketIndicator.add(a);
            }
        }
    }

    public SendPhoto getNextSendPhoto(long chatId) {
        Question current = getNextQuestion();
        InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardButtonList(current.getCountOfButton());
        inlineKeyboardMarkup.getKeyboard().get(0).get(current.getCorrectButon()).setCallbackData("true");
        return (new SendPhoto().builder()
                .replyMarkup(inlineKeyboardMarkup)
                .photo(new InputFile(current.getUrl()))
                .caption(current.getCaption())
                .chatId(chatId + "")
                .build());
    }
    private InlineKeyboardMarkup createInlineKeyboardButtonList(int countOfButton) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup in = new InlineKeyboardMarkup();
        for (Integer i = 1; i < countOfButton; i++) {
            captionText++;
            buttons.add(new InlineKeyboardButton().builder()
                    .callbackData("false")
                    .text(i.toString()+"✅")
                    .build());
        }
        in.setKeyboard(Collections.singletonList((buttons)));
        return in;
    }

    public boolean isEnd() {

        return questions.size() == currentQuestion;
    }

    public Question getNextQuestion() {
        return questions.get(currentQuestion++);

    }
}

