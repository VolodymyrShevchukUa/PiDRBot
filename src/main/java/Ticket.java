import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ticket {

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
        InlineKeyboardMarkup inlineKeyboardMarkup = keyBoardList.keyBoardobj();
        inlineKeyboardMarkup.getKeyboard().get(0).get(current.getCorrectButon());
        return (new SendPhoto().builder()
                .replyMarkup(inlineKeyboardMarkup)
                .photo(new InputFile(current.getUrl()))
                .caption(current.getCaption())
                .chatId(chatId + "")
                .build());
    }


    public boolean isEnd() {

        return questions.size() == currentQuestion;
    }

    public Question getNextQuestion() {
        return questions.get(currentQuestion++);

    }
}

