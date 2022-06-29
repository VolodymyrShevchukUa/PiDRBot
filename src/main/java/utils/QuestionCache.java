package utils;

import entity.Question;
import utils.json.JSONToQuestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class QuestionCache {

    private static final Map<Integer, List<Question>> QUESTIONS_BY_THEME = Collections.unmodifiableMap(getQuestionsMapByTheme());

    private static final Map<Double, Integer> QUESTION_THEME_MAPPING = Collections.unmodifiableMap(getQuestionsMapping());

    private static final List<Question> QUESTIONS_LIST = Collections.unmodifiableList(QUESTIONS_BY_THEME.values().stream().flatMap(Collection::stream).collect(Collectors.toList()));

    public static final String TEXT_VERSION_OF_LIST_THEME = getIt();

    private static final Random RANDOM = new Random();

    private QuestionCache() {
    }

    private static Map<Double, Integer> getQuestionsMapping() {
        Map<Double, Integer> result = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/themes.txt");
            Scanner sc = new Scanner(fis);
            int i = 1;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String substring = s.substring(0, s.indexOf(" "));
                double d = Double.parseDouble(substring.substring(0, substring.length() - 1));
                result.put(d, i);
                i++;
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getIt() {
        StringBuilder text = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/themes.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                text.append(sc.nextLine()).append("\n");
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    private static Map<Integer, List<Question>> getQuestionsMapByTheme() {
        Map<Integer, List<Question>> result = new HashMap<>();
        for (File f : new File("src/main/resources/questions").listFiles()) {
            String name = f.getName();
            int theme = Integer.parseInt(name.substring(0, name.indexOf(".json")));
            List<Question> questions = new JSONToQuestion("src/main/resources/questions/" + name).questions;
            result.put(theme, questions);
        }
        return result;
    }

    private static Queue<Question> getRandomizedQueue(int size, List<Question> list) {
        Set<Integer> ticketIndicator = new HashSet<>();
        Queue<Question> questions = new LinkedList<>();
        int totalSize = list.size();
        while (questions.size() < size) {
            int a = RANDOM.nextInt(totalSize);
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

    public static Queue<Question> getQuestionsListWithSizeAndSubject(int size, double theme) {
        return getRandomizedQueue(size, QUESTIONS_BY_THEME.get(QUESTION_THEME_MAPPING.get(theme)));
    }

    public static boolean validateThemeNumber(double theme) {
        return QUESTION_THEME_MAPPING.containsKey(theme);
    }
}
