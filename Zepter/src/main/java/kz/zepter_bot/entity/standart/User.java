package kz.zepter_bot.entity.standart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private int    id;
    private long   chatId;
    private String fullName;
    private String userName;
    private long   CompanyId;
}
