package com.viafoura.users_api.core.ports.outgoing;

import com.viafoura.users_api.adapters.outgoing.db.RepositoryException;
import com.viafoura.users_api.core.domain.User;

public interface UserManipulation {
    void delete(String email) throws RepositoryException;

    User updateOrCreate(User updatedUser) throws RepositoryException;
}
