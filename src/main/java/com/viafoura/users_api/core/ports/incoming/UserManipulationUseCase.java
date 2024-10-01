package com.viafoura.users_api.core.ports.incoming;

import com.viafoura.users_api.adapters.outgoing.db.RepositoryException;
import com.viafoura.users_api.adapters.outgoing.http.AvatarServiceException;
import com.viafoura.users_api.core.domain.User;


public interface UserManipulationUseCase {

    User createUser(User user) throws AvatarServiceException, RepositoryException;

    User updateUser(User user) throws RepositoryException;

    void deleteUser(String id) throws RepositoryException;
}
