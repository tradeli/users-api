package com.viafoura.users_api.adapters.incoming.http;

import com.viafoura.users_api.adapters.incoming.http.dto.UserDTO;
import com.viafoura.users_api.adapters.outgoing.db.RepositoryException;
import com.viafoura.users_api.adapters.outgoing.http.AvatarServiceException;
import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.outgoing.AvatarProvider;
import com.viafoura.users_api.core.ports.outgoing.UserManipulation;
import com.viafoura.users_api.core.services.UserManipulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserManipulationControllerTest {

    @Mock
    AvatarProvider avatarProvider;
    @Mock
    UserManipulation userManipulation;
    @InjectMocks
    UserManipulationService userService;
    UserManipulationController controller;

    @BeforeEach
    void setup() {
        controller = new UserManipulationController(userService);
    }


    @Test
    void createUser() throws AvatarServiceException, RepositoryException {
        when(avatarProvider.getAvatar()).thenReturn("avatar_url");
        when(userManipulation.updateOrCreate(any())).thenReturn(new User());

        var res = controller.createUser(getDTO());

        assertEquals(HttpStatusCode.valueOf(201), res.getStatusCode());
    }

    @Test
    void erroCreateUser() {

        assertThrows(InvalidParameterException.class, ()->controller.createUser(new UserDTO()));
    }

    @Test
    void updateUser() throws AvatarServiceException, RepositoryException {
        when(userManipulation.updateOrCreate(any())).thenReturn(new User());


        var res = controller.updateUser("email",getDTO());

        assertEquals(HttpStatusCode.valueOf(200), res.getStatusCode());
    }

    @Test
    void deleteUser() throws AvatarServiceException, RepositoryException {

        var res = controller.deleteUser("email");

        assertEquals(HttpStatusCode.valueOf(204), res.getStatusCode());
    }

    private UserDTO getDTO() {
        var dto = new UserDTO();
        dto.setEmail("test");
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setFriends(new ArrayList<>());
        return dto;
    }
}