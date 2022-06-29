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
    public static final String PATH = "src/main/resources/questions/%d.json";

    public static void main(String[] args) {
        int j = 1;
        for (int i = 1; i <= 147; i++) {
            String url1 = "https://vodiy.ua/pdr/test/?complect=6&theme=" + i;
            List<Question> questions = new Pars(url1).pars();
            try {
                questions.addAll(new Pars(url1 + "&part=2").pars());
                questions.addAll(new Pars(url1 + "&part=3").pars());
            } catch (Exception e) {
                e.printStackTrace();
                //page didn't find
            }
            if (questions.isEmpty()) {
                continue;
            }
            JSONArray array = new JSONArray();
            for (Question q : questions) {
                array.put(q.toJSONObject());
            }
            JSONObject result = new JSONObject();
            result.put(QUESTIONS, array);
            String fileName = String.format(PATH, j++);
            try {
                Files.writeString(Paths.get(fileName), result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
