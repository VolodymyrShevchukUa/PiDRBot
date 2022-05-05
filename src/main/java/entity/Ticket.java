package entity;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

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



    public boolean isEnd() {

        return questions.size() == currentQuestion;
    }

    public Question getNextQuestion() {
        return questions.get(currentQuestion++);

    }
}

