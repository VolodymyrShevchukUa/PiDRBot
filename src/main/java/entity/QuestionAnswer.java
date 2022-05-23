package entity;

public class QuestionAnswer {
    private final boolean isAnswerTrue;

    public QuestionAnswer(boolean isAnswerTrue) {
        this.isAnswerTrue = isAnswerTrue;
    }


    public static QuestionAnswer parseCallbackQueryData(String data) {
        return new QuestionAnswer(Boolean.parseBoolean(data));
    }

    public boolean isCurrentQuestionTrue() {
        return isAnswerTrue;
    }

}
