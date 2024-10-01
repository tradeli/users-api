package com.viafoura.users_api.adapters.outgoing.db;

import com.viafoura.users_api.adapters.outgoing.db.entities.EntityMapper;
import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.outgoing.UserRetrieval;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRetrievalImpl implements UserRetrieval {

    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    public UserRetrievalImpl(UserRepository userRepository, EntityMapper entityMapper) {
        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }


    @Override
    public User findByEmail(String email) {
        var optionalUser = userRepository.findById(email);

        return optionalUser.map(entityMapper::toDomain).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return entityMapper.toDomain(userRepository.findAll());
    }
}
