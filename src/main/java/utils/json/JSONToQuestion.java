package utils.json;

import entity.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JSONToQuestion {

    public final List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public JSONToQuestion(String fileName) {
        List<Question> tempQuestionsList;
        String stringJSON;
        try {
            stringJSON = readUsingBufferedReader(fileName);
            JSONArray jsonQuestions = new JSONObject(stringJSON).getJSONArray(QuestionToJSON.QUESTIONS);
            tempQuestionsList = new ArrayList<>(jsonQuestions.length());
            for (int i = 0; i < jsonQuestions.length(); i++) {
                tempQuestionsList.add(new Question(jsonQuestions.getJSONObject(i)));
            }
        } catch (IOException e) {
            tempQuestionsList = new ArrayList<>();
        }
        this.questions = Collections.unmodifiableList(tempQuestionsList);
    }

    private static String readUsingBufferedReader(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
    }
}
