package com.viafoura.users_api.core.services;

import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.incoming.UserRetrievalUseCase;
import com.viafoura.users_api.core.ports.outgoing.UserRetrieval;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRetrievalService implements UserRetrievalUseCase {

    private final UserRetrieval userReadOperations;

    public UserRetrievalService(UserRetrieval userReadOperations) {
        this.userReadOperations = userReadOperations;
    }

    public User getUser(String email) {
        return userReadOperations.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userReadOperations.findAll();
    }

}

