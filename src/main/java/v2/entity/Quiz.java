package v2.entity;

import entity.Question;
import org.glassfish.grizzly.utils.Pair;

public class Quiz {
    private final Pair<Question, Question> pair;

    public Quiz(Pair<Question, Question> pair) {
        this.pair = pair;
    }

    public Question getCurrentQuestion() {
        return pair.getFirst();
    }

    public Question getNextQuestion() {
        return pair.getSecond();
    }

    public boolean hasNextQuestion() {
        return pair.getSecond() != null;
    }
}
