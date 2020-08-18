package kz.zepter_bot.command.implement;

import kz.zepter_bot.command.Command;
import kz.zepter_bot.entity.standart.Language;
import kz.zepter_bot.service.LanguageService;
import kz.zepter_bot.util.Const;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class id002_SelectLang extends Command {

    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        chosenLanguage();
        return EXIT;
    }


    private void chosenLanguage() {
        if (isButton(Const.RU_LANGUAGE)) {
            LanguageService.setLanguage(chatId, Language.ru);
        }
        if (isButton(Const.KZ_LANGUAGE)) {
            LanguageService.setLanguage(chatId, Language.kz);
        }
        if (isButton(Const.EN_LANGUAGE)) {
            LanguageService.setLanguage(chatId, Language.en);
        }
    }
//
//    private int

}
