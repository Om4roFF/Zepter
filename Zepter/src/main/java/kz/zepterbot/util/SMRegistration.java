package kz.zepterbot.util;

import kz.zepterbot.entity.standart.SalesManagerUser;
import kz.zepterbot.entity.standart.User;
import kz.zepterbot.util.type.WaitingType;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SMRegistration {

    private BotUtil botUtil;
    private long chatId;
    private WaitingType waitingType = WaitingType.START;
    private SalesManagerUser smUser;

    public boolean isRegistration(Update update, BotUtil botUtil) throws TelegramApiException {
        if (botUtil == null || chatId == 0) {
            chatId = UpdateUtil.getChatId(update);
            this.botUtil = botUtil;
        }
        switch (waitingType) {
            case START:
                smUser = new SalesManagerUser();
                smUser.setChatId(chatId);
                getName();
                waitingType = WaitingType.FULL_NAME;
                return false;
            case FULL_NAME:
                if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().length() <= 50) {
                    smUser.setFullName(update.getMessage().getText());
                    getPhone();
                    waitingType = WaitingType.PHONE;
                } else {
                    wrongData();
                    getName();
                }
                return false;
            case PHONE:
                if (botUtil.hasContactOwner(update)) {
                    smUser.setPhone(update.getMessage().getContact().getPhoneNumber());
                    return true;
                } else {
                    wrongData();
                    getPhone();
                    return false;
                }
        }
        return true;
    }

    private int getName() throws TelegramApiException {
        return botUtil.sendMessage(Const.GET_FULL_NAME_FROM_REGISTRATION, chatId);
    }

    private int getPhone() throws TelegramApiException {
        return botUtil.sendMessage(Const.GET_PHONE_NUMBER_FROM_REGISTRATION, chatId);
    }

    private int wrongData() throws TelegramApiException {
        return botUtil.sendMessage(Const.WRONG_DATA_MESSAGE, chatId);
    }

    public SalesManagerUser getSmUser() {
        return smUser;
    }
}
