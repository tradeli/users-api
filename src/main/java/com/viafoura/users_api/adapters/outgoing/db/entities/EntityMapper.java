package com.viafoura.users_api.adapters.outgoing.db.entities;

import com.viafoura.users_api.core.domain.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class EntityMapper {
    static EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);


    public abstract User toDomain(UserEntity userEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


    public abstract UserEntity fromDomain(User user, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    public abstract List<User> toDomain(List<UserEntity> users);

    @AfterMapping
    protected void afterMapping(@MappingTarget User target, UserEntity source, @Context CycleAvoidingMappingContext context) {
        context.storeMappedInstance(source, target);
    }

    //Needed to avoid infinite cyclic conversion leading to StackOverflow :D
    protected User map(UserEntity entity, @Context CycleAvoidingMappingContext context) {
        if (entity == null) {
            return null;
        }

        // Check if the instance has already been mapped
        User existingInstance = context.getMappedInstance(entity, User.class);
        if (existingInstance != null) {
            return existingInstance;
        }

        // Map the properties
        User dto = new User();
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAvatar(entity.getAvatar());

        // Store the mapped instance
        context.storeMappedInstance(entity, dto);

        // Map friends
        if (entity.getFriends() != null) {
            List<User> friendsDto = new ArrayList<>();
            for (UserEntity friend : entity.getFriends()) {
                friendsDto.add(map(friend, context));
            }
            dto.setFriends(friendsDto);
        }

        return dto;
    }

    //Needed to avoid infinite cyclic conversion leading to StackOverflow :D
    public static class CycleAvoidingMappingContext {
        private final Map<Object, Object> knownInstances = new IdentityHashMap<>();

        @SuppressWarnings("unchecked")
        public <T> T getMappedInstance(Object source, Class<T> targetType) {
            return (T) knownInstances.get(source);
        }

        public void storeMappedInstance(Object source, Object target) {
            knownInstances.put(source, target);
        }
    }
}
