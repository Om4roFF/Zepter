package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.entity.custom.Complaints;
import kz.zepterbot.entity.custom.Offers;
import kz.zepterbot.entity.standart.Language;
import kz.zepterbot.service.LanguageService;
import kz.zepterbot.util.Const;
import kz.zepterbot.util.type.WaitingType;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id007_ComplaintsAndSuggestions extends Command {
    Complaints complaints = new Complaints();
    Offers offers = new Offers();

    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        switch (waitingType){
            case START:
                chooseButton();
                waitingType = WaitingType.CHOOSE_OPTION;
                return COMEBACK;
            case CHOOSE_OPTION:
                if (isButton(kz.zepterbot.util.Const.COMPLAINTS)){
                    sendMessage(Const.WRITE_SENTENCE,chatId);
                    waitingType = WaitingType.COMPLAINT;
                    return COMEBACK;
                }
                if (isButton(kz.zepterbot.util.Const.OFFERS)){
                    sendMessage(Const.WRITE_SENTENCE,chatId);
                    waitingType = WaitingType.OFFER;
                    return COMEBACK;
                }
            case COMPLAINT:
                if (hasMessageText()){
                    complaints.setText(updateMessageText);
                    complaintsDao.insert(complaints);
                    String text = messageDao.getMessageText(Const.SEND_APPEAL);
                    int number = complaintsDao.getLastId();
                    String num = Integer.toString(number);
                    String msg = text.replace("%",num );
                    sendMessage(msg,chatId);
                    return EXIT;
                }
            case OFFER:
                if (hasMessageText()){
                    offers.setText(updateMessageText);
                    offersDao.insert(offers);
                    String text = messageDao.getMessageText(Const.SEND_APPEAL);
                    int number = offersDao.getLastId();
                    String num = Integer.toString(number);
                    String msg = text.replace("%",num );
                    sendMessage(msg,chatId);
                    return EXIT;
                }
        }
        return EXIT;
    }


    public int chooseButton() throws TelegramApiException{
        return botUtils.sendMessage(kz.zepterbot.util.Const.CHOOSE_BUTTON,chatId);
    }
}
