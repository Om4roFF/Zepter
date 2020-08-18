package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.util.type.WaitingType;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id007_ComplaintsAndSuggestions extends Command {
    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        switch (waitingType){
            case START:
                chooseButton();
                waitingType = WaitingType.CHOOSE_OPTION;
                return COMEBACK;
            case CHOOSE_OPTION:
                if (isButton(kz.zepterbot.util.Const.COMPLAINTS)){
                    
                }
                if (isButton(kz.zepterbot.util.Const.OFFERS)){

                }
        }
        return EXIT;
    }


    public int chooseButton() throws TelegramApiException{
        return botUtils.sendMessage(kz.zepterbot.util.Const.CHOOSE_BUTTON,chatId);
    }
}
