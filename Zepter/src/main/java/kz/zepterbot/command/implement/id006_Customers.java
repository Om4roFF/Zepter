package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.util.Const;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id006_Customers extends Command {
    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        getMainMenu();
        return EXIT;
    }

    public int getMainMenu() throws TelegramApiException{
        return botUtils.sendMessage(kz.zepterbot.util.Const.GO_TO_MENU_FOR_CUSTOMERS,chatId);
    }
}
