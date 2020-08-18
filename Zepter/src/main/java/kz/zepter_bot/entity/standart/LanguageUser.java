package kz.zepter_bot.entity.standart;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class LanguageUser {
    private long     chatId;
    private Language language;
}
