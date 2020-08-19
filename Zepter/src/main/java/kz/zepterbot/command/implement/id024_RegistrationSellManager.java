package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.util.Registration;
import kz.zepterbot.util.SMRegistration;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class id024_RegistrationSellManager extends Command {

    private SMRegistration smRegistration = new SMRegistration();

    @Override
    public boolean execute() throws TelegramApiException{
        deleteMessage(updateMessageId);
        if (!isSMRegistered()) {
            if (!smRegistration.isRegistration(update, botUtils)) {
                return COMEBACK;
            } else {
                salaesManagerUserDao.insert(smRegistration.getSmUser());
                sendMessageWithAddition();
                return EXIT;
            }
        } else {
            sendMessageWithAddition();

            return EXIT;
        }
    }
}
