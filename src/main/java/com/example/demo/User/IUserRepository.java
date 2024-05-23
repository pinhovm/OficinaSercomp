package com.example.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findById(UUID id);
    UserModel findByUsername (String username);

}
