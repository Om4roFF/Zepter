package kz.zepterbot.config;

import kz.zepterbot.command.Command;
import kz.zepterbot.dao.DaoFactory;
import kz.zepterbot.dao.implement.MessageDao;
import kz.zepterbot.entity.standart.Language;
import kz.zepterbot.entity.standart.Message;
import kz.zepterbot.exeption.CommandNotFoundException;
import kz.zepterbot.service.CommandService;
import kz.zepterbot.service.LanguageService;
import kz.zepterbot.util.Const;
import kz.zepterbot.util.DateUtil;
import kz.zepterbot.util.SetDeleteMessages;
import kz.zepterbot.util.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.Date;

@Slf4j
public class Conversation {

    private CommandService commandService = new CommandService();
    private Command command;
    private Long chatId;
    private static long currentChatId;
    private DaoFactory factory = DaoFactory.getFactory();
    private MessageDao messageDao;

    public static long getCurrentChatId() {
        return currentChatId;
    }

    public void handleUpdate(Update update, DefaultAbsSender bot) throws SQLException, TelegramApiException {
        printUpdate(update);
        chatId = UpdateUtil.getChatId(update);
        currentChatId = chatId;
        messageDao = factory.getMessageDao();
        checkLang(chatId);
        try {
            command = commandService.getCommand(update);
            if (command != null) {
                SetDeleteMessages.deleteKeyboard(chatId, bot);
                SetDeleteMessages.deleteMessage(chatId, bot);
            }
        } catch (CommandNotFoundException e) {
            if (chatId < 0) {
                return;
            }
            if (command == null) {
                SetDeleteMessages.deleteKeyboard(chatId, bot);
                SetDeleteMessages.deleteMessage(chatId, bot);
                Message message = messageDao.getMessage(Const.COMMAND_NOT_FOUND);
                SendMessage sendMessage = new SendMessage(chatId, message.getName());
                bot.execute(sendMessage);
            }
        }
        if (command != null) {
            if (command.isInitNotNormal(update, bot)) {
                clear();
                return;
            }
            boolean commandFinished = command.execute();
            if (commandFinished) {
                clear();
            }
        }
    }

    private void checkLang(long chatId) {
        if (LanguageService.getLanguage(chatId) == null) {
            LanguageService.setLanguage(chatId, Language.ru);
        }
    }

    private void printUpdate(Update update) {
        String dateMessage = "";
        if (update.hasMessage()) {
            dateMessage = DateUtil.getDbMmYyyyHhMmSs(new Date((long) update.getMessage().getDate() * 1000));
        }
        log.debug("New update get {} -> send response {}", dateMessage, DateUtil.getDbMmYyyyHhMmSs(new Date()));
        log.debug(UpdateUtil.toString(update));
    }

//    public static DefaultAbsSender getBot() {
//        return Main.getBot();
//    }

    void clear() {
        command.clear();
        command = null;
    }
}
