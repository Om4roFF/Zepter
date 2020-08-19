package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.entity.custom.Office;
import kz.zepterbot.util.Const;
import kz.zepterbot.util.type.WaitingType;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id004_Office extends Command {
    Office users = new Office();
    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        switch (waitingType){
            case START:
                getFullName();
                waitingType = WaitingType.SET_FULL_NAME;
                return COMEBACK;
            case SET_FULL_NAME:
                if (hasMessageText() && updateMessageText.length()<=30){
                    getContact();
                    users.setFullName(updateMessageText);
                    waitingType = WaitingType.SET_MOBILE_PHONE_NUMBER;
                }
                return COMEBACK;
            case SET_MOBILE_PHONE_NUMBER:
                users.setPhone(updateMessageText);
                officeDao.insert(users);
                goToMainMenu();

        }
        return EXIT;
    }

    public int getFullName() throws TelegramApiException {
        return botUtils.sendMessage(kz.zepterbot.util.Const.SET_FULL_NAME,chatId);
    }

    public int getContact() throws TelegramApiException{
        return botUtils.sendMessage(kz.zepterbot.util.Const.SET_MOBILE_PHONE_NUMBER,chatId);
    }

    public int goToMainMenu() throws TelegramApiException{
        return botUtils.sendMessage(kz.zepterbot.util.Const.GO_TO_MAIN_MENU,chatId);
    }
}
