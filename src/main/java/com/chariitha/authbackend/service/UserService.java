package com.chariitha.authbackend.service;

import com.chariitha.authbackend.model.User;
import com.chariitha.authbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Signup user
    public User registerUser(User user) {
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("User with this email already exists!");
        }
        return userRepository.save(user);
    }

    // Login check
    public User loginUser(String username, String password) {
        Optional<User> existing = userRepository.findByUsername(username);
        if (existing.isPresent() && existing.get().getPassword().equals(password)) {
            return existing.get();
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
