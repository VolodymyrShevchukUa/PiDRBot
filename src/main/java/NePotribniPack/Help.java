package NePotribniPack;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Help extends BotCommand {

    private final ICommandRegistry commandRegistry;

    public Help(ICommandRegistry commandRegistry) {
        super("help", "Get all anal");
        this.commandRegistry = commandRegistry;
    }




    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        this.execute(absSender, message.getFrom(), message.getChat(), arguments);

       
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        
    }
}


// Не шарю як це працює ніхуя
//    @Override
//    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
//
//
//        if (!DatabaseManager.getInstance().getUserStateForCommandsBot(user.getId())) {
//            return;
//        }
//
//        StringBuilder helpMessageBuilder = new StringBuilder("<b>NePotribniPack.Help</b>\n");
//        helpMessageBuilder.append("These are the registered commands for this Bot:\n\n");
//
//        for (IBotCommand botCommand : commandRegistry.getRegisteredCommands()) {
//            helpMessageBuilder.append(botCommand.toString()).append("\n\n");
//        }
//
//        SendMessage helpMessage = new SendMessage();
//        helpMessage.setChatId(chat.getId().toString());
//        helpMessage.enableHtml(true);
//        helpMessage.setText(helpMessageBuilder.toString());
//
//        try {
//            absSender.execute(helpMessage);
//        } catch (TelegramApiException e) {
//            BotLogger.error(LOGTAG, e);
//        }
//
//    }
//}

