package com.viafoura.users_api.adapters.outgoing.db;

import com.viafoura.users_api.adapters.outgoing.db.entities.EntityMapper;
import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.outgoing.UserManipulation;
import org.springframework.stereotype.Service;

@Service
public class UserManipulationImpl implements UserManipulation {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    public UserManipulationImpl(UserRepository userRepository, EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public void delete(String email) throws RepositoryException {
        try {
            userRepository.deleteById(email);
        } catch (Exception e) {
            throw new RepositoryException("Error attempting to delete user", e);
        }
    }

    @Override
    public User updateOrCreate(User updatedUser) throws RepositoryException {
        try {
            return entityMapper.toDomain(userRepository.save(entityMapper.fromDomain(updatedUser)));
        } catch (Exception e) {
            throw new RepositoryException("Error attempting to updateOrDelete", e);
        }
    }
}
