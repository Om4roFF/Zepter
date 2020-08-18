package kz.zepter_bot.command.implement;

import kz.zepter_bot.command.Command;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id001_ShowInfo extends Command {

    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        sendMessageWithAddition();
        deleteMessage(updateMessageId);
        return EXIT;
    }
}
