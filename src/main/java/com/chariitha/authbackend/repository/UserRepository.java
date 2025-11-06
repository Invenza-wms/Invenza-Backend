package com.chariitha.authbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chariitha.authbackend.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 👇 Add this line — Spring Data JPA will automatically implement it
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    // To find user by username
    Optional<User> findByUsername(String username);
}

