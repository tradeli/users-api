package com.viafoura.users_api.adapters.incoming.http;

import com.viafoura.users_api.adapters.incoming.http.dto.UserDTO;
import com.viafoura.users_api.adapters.incoming.http.dto.UserDTOMapper;
import com.viafoura.users_api.adapters.outgoing.db.RepositoryException;
import com.viafoura.users_api.adapters.outgoing.http.AvatarServiceException;
import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.incoming.UserManipulationUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserManipulationController {
    private static final Logger LOGGER = LogManager.getLogger(UserManipulationController.class);

    private final UserManipulationUseCase userManagement;

    public UserManipulationController(UserManipulationUseCase userManagement) {
        this.userManagement = userManagement;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) throws AvatarServiceException, RepositoryException {
        LOGGER.info("New request to create user");
        var createdUser = userManagement.createUser(UserDTOMapper.INSTANCE.toDomain(user));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody UserDTO userDetails) throws RepositoryException {
        LOGGER.info("New request to update user");
        var updatedUser = UserDTOMapper.INSTANCE.toDomain(userDetails);
        userManagement.updateUser(updatedUser);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) throws RepositoryException {
        LOGGER.info("New request to delete user");
        userManagement.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

}
