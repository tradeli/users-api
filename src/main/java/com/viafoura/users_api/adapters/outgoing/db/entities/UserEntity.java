package com.viafoura.users_api.adapters.outgoing.db.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viafoura.users_api.core.domain.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "friend_email")
    )
    @JsonIgnoreProperties("friends")
    private List<UserEntity> friends = new ArrayList<>();

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

    public List<UserEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<UserEntity> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity userEntity)) return false;
        return userEntity.getEmail().equals(getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }


    public User toDomain() {
        return EntityMapper.INSTANCE.toDomain(this, new EntityMapper.CycleAvoidingMappingContext());
    }
}
