package utils;

import entity.Question;
import utils.json.JSONToQuestion;
import utils.json.QuestionToJSON;

import java.util.*;

public class QuestionCache {
    private static final List<Question> QUESTIONS_LIST = Collections.unmodifiableList(new JSONToQuestion(QuestionToJSON.PATH).getQuestions());
    //#TODO
    public static final Set<String> SET_OF_SUBJECT = null;

    private static Queue<Question> getRandomizedQueue(int size, List<Question> list) {
        Set<Integer> ticketIndicator = new HashSet<>();
        Queue<Question> questions = new LinkedList<>();
        int totalSize = list.size();
        Random r = new Random();
        while (questions.size() < size) {
            int a = r.nextInt(totalSize);
            if (!ticketIndicator.contains(a)) {
                questions.add(list.get(a));
                ticketIndicator.add(a);
            }
        }
        return questions;
    }

    public static Queue<Question> getQuestionsListWithSize(int size) {
        return getRandomizedQueue(size, QUESTIONS_LIST);
    }

    public static Queue<Question> getQuestionsListWithSizeAndSubject(int size, String subject) {
        List<Question> list = QUESTIONS_LIST;//#TODO get list by subject
        return getRandomizedQueue(size, list);
    }
}
