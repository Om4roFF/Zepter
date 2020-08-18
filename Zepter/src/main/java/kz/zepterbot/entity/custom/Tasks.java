package kz.zepterbot.entity.custom;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class Tasks {
    private int id;
    private long chat_id;
    private String text;
    private String audio;
    private String photo;
    private String video;
    private long developer;

}
