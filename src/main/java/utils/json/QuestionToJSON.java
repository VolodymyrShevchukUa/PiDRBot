package utils.json;

import entity.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class QuestionToJSON {
    public static final String QUESTIONS = "Questions";
    public static final String PATH = "src/main/resources/Questions.json";

    public static void main(String[] args) {
        List<Question> questions = new Pars("https://vodiy.ua/pdr/test/?complect=6&bilet=", 100).pars();
        JSONArray array = new JSONArray();
        for (Question q : questions) {
            array.put(q.toJSONObject());
        }
        JSONObject result = new JSONObject();
        result.put(QUESTIONS, array);
        try {
            Files.writeString(Paths.get(PATH), result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
