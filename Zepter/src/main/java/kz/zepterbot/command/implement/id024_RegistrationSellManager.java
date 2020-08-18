package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.util.Registration;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id024_RegistrationSellManager extends Command {

    private Registration registration = new Registration();
    private StringBuilder sb = new StringBuilder();


    @Override
    public boolean execute() throws TelegramApiException,  SQLException {
        deleteMessage(updateMessageId);
        if (!isRegistered()) {
            if (!registration.isRegistration(update, botUtils)) {
                return COMEBACK;
            } else {
                userDao.insert(registration.getUser());
                sendMessageWithAddition();
                return EXIT;
            }
        } else {
            sendMessageWithAddition();

            return EXIT;
        }
    }
}
