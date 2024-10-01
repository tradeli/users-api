package com.viafoura.users_api.core.domain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;


public class User {
    //TODO: refactor to a Builder style with final variables (immutability)
    private String email;
    private String firstName;
    private String lastName;

    //Avatar is optional, so no checking and not necessary to be immutable
    private String avatar;

    private List<User> friends;


    public User(String email, String firstName, String lastName, String avatar, List<User> friends) {
        //TODO: could be a list of errors so the user don't have to keep retrying

        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAvatar(avatar);
        this.setFriends(friends);
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    //TODO: could be a list of errors so the user don't have to keep retrying
    public void setEmail(String email) {
        if (email == null || email.isBlank())
            throw new InvalidParameterException("invalid email");
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank())
            throw new InvalidParameterException("invalid first name");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank())
            throw new InvalidParameterException("invalid last name");
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends == null ? new ArrayList<User>() : friends;
    }
}
