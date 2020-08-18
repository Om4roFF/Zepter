package kz.zepter_bot.entity.custom;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter@Setter
public class Report {
    private int id;
    private String nameOfBot;
    private String audio;
    private String message;
    private String photo;
}
