package com.viafoura.users_api.adapters.incoming.http;

import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.incoming.UserRetrievalUseCase;
import com.viafoura.users_api.core.ports.outgoing.AvatarProvider;
import com.viafoura.users_api.core.ports.outgoing.UserManipulation;
import com.viafoura.users_api.core.ports.outgoing.UserRetrieval;
import com.viafoura.users_api.core.services.UserManipulationService;
import com.viafoura.users_api.core.services.UserRetrievalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRetrievalControllerTest {

    @Mock
    AvatarProvider avatarProvider;
    @Mock
    UserRetrieval userRetrieval;
    @InjectMocks
    UserRetrievalService userService;

    UserRetrievalController controller;


    @BeforeEach
    void setup() {
        controller = new UserRetrievalController(userService);
    }
    @Test
    void getUser() {
        when(userRetrieval.findByEmail(any())).thenReturn(new User());

        var res = controller.getUser("email");

        assertEquals(HttpStatusCode.valueOf(200), res.getStatusCode());
    }

    @Test
    void getAllUsers() {
        when(userRetrieval.findAll()).thenReturn(new ArrayList<User>());

        var res = controller.getAllUsers();

        assertEquals(HttpStatusCode.valueOf(200), res.getStatusCode());
    }
}