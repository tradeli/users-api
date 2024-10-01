package com.viafoura.users_api.adapters.outgoing.db.entities;

import com.viafoura.users_api.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    User toDomain(UserEntity userEntity);

    UserEntity fromDomain(User user);

    List<User> toDomain(List<UserEntity> users);
}
