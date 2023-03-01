package kg.megacom.authorizationservice.models.dtos;

import kg.megacom.authorizationservice.models.entities.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDto {
    Long id;
    String name;
    Account account;
    Date addDate;
    Date updateDate;
    boolean active;
}
