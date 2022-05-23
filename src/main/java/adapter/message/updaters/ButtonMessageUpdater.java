package adapter.message.updaters;

import adapter.message.EditMessageReplyMarkupMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ButtonMessageUpdater {
    private final Message message;

    public ButtonMessageUpdater(Message message) {
        this.message = message;
    }

    public EditMessageReplyMarkupMessage addTextToButton(int rowIndex, int columnIndex, String text) {
        InlineKeyboardMarkup replyMarkup = message.getReplyMarkup();
        InlineKeyboardButton inlineKeyboardButton = replyMarkup.getKeyboard().get(rowIndex).get(columnIndex);
        inlineKeyboardButton.setText(inlineKeyboardButton.getText() + text);
        EditMessageReplyMarkupMessage editReplyMarkup = new EditMessageReplyMarkupMessage();
        editReplyMarkup.setReplyMarkup(replyMarkup);
        editReplyMarkup.setMessageId(message.getMessageId());
        return editReplyMarkup;
    }
    public EditMessageReplyMarkupMessage showAnswer() {
        InlineKeyboardMarkup replyMarkup = message.getReplyMarkup();
        for(List<InlineKeyboardButton> i:replyMarkup.getKeyboard()){
            for (InlineKeyboardButton j:i){
                j.setText(j.getText() + (Boolean.parseBoolean(j.getCallbackData())?"✅":"❌"));
            }
        }
        EditMessageReplyMarkupMessage editReplyMarkup = new EditMessageReplyMarkupMessage();
        editReplyMarkup.setReplyMarkup(replyMarkup);
        editReplyMarkup.setMessageId(message.getMessageId());
        return editReplyMarkup;
    }
}
