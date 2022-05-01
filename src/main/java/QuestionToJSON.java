import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class QuestionToJSON {
    public static void main(String[] args) {


       List<Question> questions = new Pars("https://vodiy.ua/pdr/test/?complect=6&bilet=",100).pars();
       System.out.println();
       JSONArray array = new JSONArray();
       for(Question q: questions){
           JSONObject jsonObject = new JSONObject();
           jsonObject.put("Caption",q.getCaption());
           jsonObject.put("CorrectButton",q.getCorrectButon());
           jsonObject.put("Url",q.getUrl());
           jsonObject.put("CountOfButton",q.getCountOfButton());
           array.put(jsonObject);
       }
       JSONObject result = new JSONObject();
       result.put("Questions",array);
       System.out.println(result.toString());

        String content = result.toString();
        String path = "src/main/resources/Questions2.json";

        try {

            Files.writeString(Paths.get(path), content);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
