package com.viafoura.users_api.adapters.incoming.http;

import com.viafoura.users_api.adapters.incoming.http.dto.UserDTO;
import com.viafoura.users_api.adapters.incoming.http.dto.UserDTOMapper;
import com.viafoura.users_api.adapters.outgoing.db.RepositoryException;
import com.viafoura.users_api.adapters.outgoing.http.AvatarServiceException;
import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.incoming.UserManipulationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserManipulationController {

    private final UserManipulationUseCase userManagement;

    public UserManipulationController(UserManipulationUseCase userManagement) {
        this.userManagement = userManagement;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) throws AvatarServiceException, RepositoryException {
        var createdUser = userManagement.createUser(UserDTOMapper.INSTANCE.toDomain(user));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody UserDTO userDetails) throws RepositoryException {
        var updatedUser = UserDTOMapper.INSTANCE.toDomain(userDetails);
        userManagement.updateUser(updatedUser);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) throws RepositoryException {
        userManagement.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

}
