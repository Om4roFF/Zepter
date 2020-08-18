package kz.zepterbot.service;

import kz.zepterbot.dao.DaoFactory;
import kz.zepterbot.entity.standart.Language;
import kz.zepterbot.entity.standart.LanguageUser;

import java.util.HashMap;
import java.util.Map;

public class LanguageService {

    private static Map<Long, Language> langMap = new HashMap<>();

    public static Language getLanguage(long chatId) {
        Language language = langMap.get(chatId);
        if (language == null) {
            LanguageUser langUser = DaoFactory.getFactory().getLanguageUserDao().getByChatId(chatId);
            if (langUser != null) {
                language = langUser.getLanguage();
                langMap.put(chatId, language);
            }
        }
        return language;
    }

    public static void setLanguage(long chatId, Language language) {
        langMap.put(chatId, language);
        DaoFactory.getFactory().getLanguageUserDao().insertOrUpdate(new LanguageUser(chatId, language));
    }
}
