package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.util.Const;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
public class id003_SelectDepartment extends Command {
    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        welcome();
        return EXIT;
    }
    
    private int welcome() throws TelegramApiException{
        return botUtils.sendMessage(Const.WELCOME_TEXT_WHEN_START,chatId);
    }
}
