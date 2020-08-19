package kz.zepterbot.entity.standart;

import lombok.Data;

@Data
public class SalesManagerUser {
    private int id;
    private long chatId;
    private String phone;
    private String fullName;
}
