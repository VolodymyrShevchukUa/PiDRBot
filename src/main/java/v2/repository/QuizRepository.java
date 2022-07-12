package v2.repository;

import entity.quiz.Quiz;

public abstract class QuizRepository {

    private static final QuizRepository singleton = new QuizRepositoryByMap();

    public static QuizRepository getInstance() {
        return singleton;
    }

    public abstract Quiz get(long key) ;
    abstract void save(long key, Quiz quiz) ;

}
