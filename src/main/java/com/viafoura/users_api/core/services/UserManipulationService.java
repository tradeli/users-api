package com.viafoura.users_api.core.services;

import com.viafoura.users_api.adapters.outgoing.db.RepositoryException;
import com.viafoura.users_api.adapters.outgoing.http.AvatarServiceException;
import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.incoming.UserManipulationUseCase;
import com.viafoura.users_api.core.ports.outgoing.AvatarProvider;
import com.viafoura.users_api.core.ports.outgoing.UserManipulation;
import org.springframework.stereotype.Service;


@Service
public class UserManipulationService implements UserManipulationUseCase {

    private final UserManipulation userWriteOperations;
    private final AvatarProvider avatarProvider;

    public UserManipulationService(UserManipulation userWriteOperations, AvatarProvider avatarProvider) {
        this.userWriteOperations = userWriteOperations;
        this.avatarProvider = avatarProvider;
    }

    public User createUser(User user) throws AvatarServiceException, RepositoryException {
        var avatarUrl = avatarProvider.getAvatar();
        user.setAvatar(avatarUrl);

        return updateUser(user);
    }

    public User updateUser(User updatedUser) throws RepositoryException {

        return userWriteOperations.updateOrCreate(updatedUser);
    }

    public void deleteUser(String email) throws RepositoryException {

        userWriteOperations.delete(email);
    }
}

