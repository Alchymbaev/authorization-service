package kg.megacom.authorizationservice.mappers;

import kg.megacom.authorizationservice.models.entities.Users;
import kg.megacom.authorizationservice.models.dtos.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper extends BaseMapper<Users, UsersDto> {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

}
