package kz.zepter_bot.dao.enums;

import lombok.Getter;

@Getter
public enum TableNames {

    QUEST,
    SURVEY_ANSWERS,
    SURVEYS;

    private final String name;

    TableNames() {
        name = null;
    }

    TableNames(String name) {
        this.name = name;
    }
}
