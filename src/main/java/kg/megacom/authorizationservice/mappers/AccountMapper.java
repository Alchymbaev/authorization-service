package kg.megacom.authorizationservice.mappers;

import kg.megacom.authorizationservice.models.entities.Account;
import kg.megacom.authorizationservice.models.dtos.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper extends BaseMapper<Account, AccountDto> {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

}
