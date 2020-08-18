package kz.zepterbot.entity.custom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter@Getter
public class Bid {
    private int id;
    private String fullName;
    private String phone;
    private String request;
}
