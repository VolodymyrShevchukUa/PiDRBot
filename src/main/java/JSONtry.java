import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Добавити підпис до батонів
public class JSONtry {
    List<Question> questions1 = new ArrayList<>();
    JSONtry(String fileName) {
        String stringJSON = null;
        try {
            stringJSON = readUsingBufferedReader(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(stringJSON);
        JSONArray questions = jsonObject.getJSONArray(QuestionToJSON.QUESTIONS);
        for (int i = 0; i < questions.length(); i++)
        {
            String image = questions.getJSONObject(i).getString(QuestionToJSON.URL);
            int correctAnswer = questions.getJSONObject(i).getInt(QuestionToJSON.CORRECT_BUTTON);
            int countOfButtons = questions.getJSONObject(i).getInt(QuestionToJSON.COUNT_OF_BUTTON);
            String caption = questions.getJSONObject(i).getString(QuestionToJSON.CAPTION);
            questions1.add(new Question(image,correctAnswer,caption,countOfButtons));

        }
    }
    // ??????
    public Question get(int i){
        return questions1.get(i);
    }

    private static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }







}
