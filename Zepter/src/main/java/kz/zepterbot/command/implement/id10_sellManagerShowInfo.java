package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.util.Const;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id10_sellManagerShowInfo extends Command {
    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        if (!isSMRegistered()){
            sendMessage(Const.SALES_MANAGER_ACCESS);
            return EXIT;
        }
        sendMessageWithAddition();
        deleteMessage(updateMessageId);
        return EXIT;
    }
}
