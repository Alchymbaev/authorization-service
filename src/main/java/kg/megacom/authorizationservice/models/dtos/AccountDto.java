package kg.megacom.authorizationservice.models.dtos;

import kg.megacom.authorizationservice.models.enums.AccountStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDto {
    Long id;
    String login;
    String password;
    int count;
    AccountStatus status;
    Date addDate;
    Date updateDate;
    boolean active;
}
