package kz.zepterbot.entity.custom;

import lombok.Data;

@Data
public class SurveyAns {
    private int id;
    private long chat_id;
    private String answer;
    private String fullName;
}
