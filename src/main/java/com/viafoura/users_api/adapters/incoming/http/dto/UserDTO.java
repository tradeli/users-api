package com.viafoura.users_api.adapters.incoming.http.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Represents the user to be created")
public class UserDTO {
    @Schema(description = "Represents a list of users that are to be set as friends with the current user", example = "test@email.com")
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    @Schema(description = "Represents a list of users that are to be set as friends with the current user",
            example = """
                    [{"email": "email@example.com", "firstName": "string", "lastName": "string", "avatar": "string"}]""")
    private List<UserDTO> friends;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<UserDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<UserDTO> friends) {
        this.friends = friends;
    }
}
