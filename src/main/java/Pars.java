import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// робив не я
public class Pars {
    private final String url;
    private final int pages;

    public Pars(String url, int pages) {
        this.url = url;
        this.pages = pages;
    }
    public List<Question> pars() {
        List<Question> result = new ArrayList<>();
        for (int i = 1; i <= pages; i++) {
            String currentURL = url + i;
            try {
                result.addAll(parseURL(currentURL));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private List<Question> parseURL(String url) throws IOException {
        List<Question> result = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element boxOfQuestion = doc.select("ul.ticketpage_ul").first();
        for (Element curr : boxOfQuestion.children()){
            if (curr.selectFirst("div.title_ticket")==null){
                continue;
            }

//            String ticketNumber = curr.selectFirst("div.title_ticket").text();
            result.add(createQuestion(curr));
        }
        return result;
    }



    private Question createQuestion(Element current) {
        String caption = current.selectFirst("p").text();
        String image = "https://vodiy.ua" + current.selectFirst("img").attr("src");
        int index = 1;
        int correct = 0;
        for (Element e : current.select("label.label_raio ")) {
            if (e.selectFirst("input").attr("rel").equals("rt1")) {
                correct = index - 1;
            }
            caption = caption + "\n" + index + ") " + e.selectFirst("span.span_text").text();
            index++;
        }
        return new Question(image, correct, caption, index);

    }
}
