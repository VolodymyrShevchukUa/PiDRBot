package v2.repository;

import entity.quiz.Quiz;
import utils.AutoCleaningMap;

public class QuizRepositoryByMap extends QuizRepository{
    private static final AutoCleaningMap<Long, Quiz> repository = new AutoCleaningMap<>();

    public Quiz get(long key) {
        return repository.get(key);
    }

    public void save(long key, Quiz quiz) {
        repository.put(key, quiz);
    }
}
