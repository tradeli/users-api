package com.viafoura.users_api.adapters.incoming.http.dto;

import com.viafoura.users_api.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

    User toDomain(UserDTO userDTO);

    UserDTO fromDomain(User user);

    List<UserDTO> fromDomain(List<User> users);
}
