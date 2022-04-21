import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

public class CommandBotEasy {

    public static SendMessage HelpCommand(Update update) {
        SendMessage helpCommand = new SendMessage();
        String chatId = update.getMessage().getChatId().toString();
        helpCommand.setChatId(chatId);
       // helpCommand.setText("unknow command");
        switch (update.getMessage().getText()){
            case("/help"):
                helpCommand.setText("Cписок доступних тестів");
                break;
            case("/rank"):
                helpCommand.setText("rank");
                break;
            default:
                helpCommand.setText("Deffault");






        }

        return helpCommand;

    }
}