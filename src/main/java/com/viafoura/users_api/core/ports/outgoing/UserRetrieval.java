package com.viafoura.users_api.core.ports.outgoing;

import com.viafoura.users_api.core.domain.User;

import java.util.List;

public interface UserRetrieval {

    User findByEmail(String email);

    List<User> findAll();
}
