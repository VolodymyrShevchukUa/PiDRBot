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
    private Double theme = null;
    private boolean isRealTest = false;
    private boolean isWithTime = false;

    public void setCountOfQuestion(Integer countOfQuestion) {
        this.countOfQuestion = countOfQuestion;
    }

    public void setTheme(Double theme) {
        this.theme = theme;
    }

    public void setRealTest(boolean realTest) {
        isRealTest = realTest;
    }

    public void setWithTime(boolean withTime) {
        isWithTime = withTime;
    }

    public Quiz build(ChatSenderI sender) {
        if (countOfQuestion==null){
            countOfQuestion=20;
        }
        Queue<Question> questionsList = theme == null ?
                QuestionCache.getQuestionsListWithSize(countOfQuestion)
                : QuestionCache.getQuestionsListWithSizeAndSubject(countOfQuestion, theme);
        int numberOfAttempts = isRealTest ? 2 : questionsList.size();
        return isWithTime ?
                new QuizWithTime(questionsList, sender, numberOfAttempts)
                : new QuizWithMarks(questionsList, sender, numberOfAttempts);
    }
}
