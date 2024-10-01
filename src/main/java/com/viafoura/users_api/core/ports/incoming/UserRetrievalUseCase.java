package com.viafoura.users_api.core.ports.incoming;

import com.viafoura.users_api.core.domain.User;

import java.util.List;

public interface UserRetrievalUseCase {
    User getUser(String id);

    List<User> getAllUsers();
}
