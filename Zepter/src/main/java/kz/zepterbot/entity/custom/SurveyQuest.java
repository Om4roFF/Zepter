package kz.zepterbot.entity.custom;

import lombok.Data;

@Data
public class SurveyQuest {
    private int id;
    private String text;
    private String answers;
    private int panel;
}
