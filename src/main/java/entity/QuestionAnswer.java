package entity;

import org.glassfish.grizzly.utils.Pair;

public class QuestionAnswer {

    private final Pair<Boolean, Integer> userAnswer; // is current question true, index of button user clicked

    public static QuestionAnswer parseCallbackQueryData(String data) {
        String[] s = data.split(Question.SEPARATOR);
        return new QuestionAnswer(new Pair<>(Boolean.valueOf(s[0]), Integer.valueOf(s[1])));
    }

    public QuestionAnswer(Pair<Boolean, Integer> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getIndexOfUsedButton() {
        return userAnswer.getSecond();
    }

    public boolean isCurrentQuestionTrue() {
        return userAnswer.getFirst();
    }
}
