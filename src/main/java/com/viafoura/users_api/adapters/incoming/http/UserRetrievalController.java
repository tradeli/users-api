package com.viafoura.users_api.adapters.incoming.http;

import com.viafoura.users_api.core.domain.User;
import com.viafoura.users_api.core.ports.incoming.UserRetrievalUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRetrievalController {

    private final UserRetrievalUseCase userRetrieval;

    public UserRetrievalController(UserRetrievalUseCase userRetrieval) {
        this.userRetrieval = userRetrieval;
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        var user = userRetrieval.getUser(email);

        //Exceptions are expensive for performance, so null based check
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userRetrieval.getAllUsers();

        //return ok even for empty users, because it was not looking for a specific resource
        return ResponseEntity.ok(users);
    }

}
