package com.viafoura.users_api.adapters.outgoing.db;

import com.viafoura.users_api.adapters.outgoing.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // Inherits save and delete methods
}

