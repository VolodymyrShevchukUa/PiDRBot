package utils;

import entity.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class QuestionToJSON {

    public static final String CAPTION = "Caption";
    public static final String CORRECT_BUTTON = "CorrectButton";
    public static final String URL = "Url";
    public static final String COUNT_OF_BUTTON = "CountOfButton";
    public static final String QUESTIONS = "Questions";
    public static final String PATH = "src/main/resources/Questions2.json";

    public static void main(String[] args) {


       List<Question> questions = new Pars("https://vodiy.ua/pdr/test/?complect=6&bilet=",100).pars();
       System.out.println();
       JSONArray array = new JSONArray();
       for(Question q: questions){
           JSONObject jsonObject = new JSONObject();
           jsonObject.put(CAPTION,q.getCaption());
           jsonObject.put(CORRECT_BUTTON,q.getCorrectButon());
           jsonObject.put(URL,q.getUrl());
           jsonObject.put(COUNT_OF_BUTTON,q.getCountOfButton());
           array.put(jsonObject);
       }
       JSONObject result = new JSONObject();
       result.put(QUESTIONS,array);
       System.out.println(result.toString());

        String content = result.toString();

        try {
            Files.writeString(Paths.get(PATH), content);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
