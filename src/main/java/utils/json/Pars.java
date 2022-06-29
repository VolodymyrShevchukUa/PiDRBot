package utils.json;

import entity.Question;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Pars {
    private final String url;

    public Pars(String url) {
        this.url = url;
    }

    public List<Question> pars() {
        List<Question> result = new ArrayList<>();
        try {
            result.addAll(parseURL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Question> parseURL(String url) throws IOException {
        List<Question> result = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element boxOfQuestion = doc.select("ul.ticketpage_ul").first();
        for (Element curr : boxOfQuestion.children()) {
            if (curr.selectFirst("div.title_ticket") == null) {
                continue;
            }
            result.add(createQuestion(curr));
        }
        return result;
    }


    private Question createQuestion(Element current) {
        StringBuilder caption = new StringBuilder(current.selectFirst("p").text());
        String image = "https://vodiy.ua" + current.selectFirst("img").attr("src");
        int index = 1;
        int correct = 0;
        for (Element e : current.select("label.label_raio ")) {
            if (e.selectFirst("input").attr("rel").equals("rt1")) {
                correct = index - 1;
            }
            caption.append("\n").append(index).append(") ").append(e.selectFirst("span.span_text").text());
            index++;
        }
        if (image.equals("https://vodiy.ua/static/img/com_2.png")) {
            image = Question.NULL_IMAGE;
        }
        return new Question(image, correct, caption.toString(), index);

    }
}
