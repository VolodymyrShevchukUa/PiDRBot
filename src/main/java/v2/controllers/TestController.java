package v2.controllers;

import adapter.UpdateUtil;
import entity.quiz.Quiz;
import v2.models.TestModel;
import v2.repository.QuizRepository;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestController implements Controller {

    private static final QuizRepository repository = QuizRepository.getInstance();
    private final TestModel model = new TestModel(this);


    public TestController() {
    }

    public Controller onUpdateReceived(Update update) {
        Quiz quiz = repository.get(UpdateUtil.getChatId(update));
        return update.hasCallbackQuery() ? model.processCallbackQuery(update.getCallbackQuery(), quiz) : model.processMessage(update, quiz);
    }
}
