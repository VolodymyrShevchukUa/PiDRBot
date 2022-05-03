package utils;

import entity.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Добавити підпис до батонів
public class JSONToQuestion {


    public List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }
    public JSONToQuestion(String fileName) {
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
            this.questions.add(new Question(questions.getJSONObject(i)));

        }
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
