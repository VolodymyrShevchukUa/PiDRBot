package handlers.prepare.test.strategy;

import adapter.sender.ChatSenderI;
import entity.Question;
import entity.quiz.Quiz;
import entity.quiz.QuizWithMarks;
import entity.quiz.QuizWithTime;
import utils.QuestionCache;

import java.util.Queue;

public class QuizBuilder {
    private Integer countOfQuestion = null;
    private String nameOfSubject = null;
    private boolean isRealTest = false;
    private boolean isWithTime = false;

    public void setCountOfQuestion(Integer countOfQuestion) {
        this.countOfQuestion = countOfQuestion;
    }

    public void setNameOfSubject(String nameOfSubject) {
        this.nameOfSubject = nameOfSubject;
    }

    public void setRealTest(boolean realTest) {
        isRealTest = realTest;
    }

    public void setWithTime(boolean withTime) {
        isWithTime = withTime;
    }

    public Quiz build(ChatSenderI sender) {
        Queue<Question> questionsList = nameOfSubject == null ?
                QuestionCache.getQuestionsListWithSize(countOfQuestion)
                : QuestionCache.getQuestionsListWithSizeAndSubject(countOfQuestion, nameOfSubject);
        int numberOfAttempts = isRealTest ? 2 : questionsList.size();
        return isWithTime ?
                new QuizWithTime(questionsList, sender, numberOfAttempts)
                : new QuizWithMarks(questionsList, sender, numberOfAttempts);
    }
}
