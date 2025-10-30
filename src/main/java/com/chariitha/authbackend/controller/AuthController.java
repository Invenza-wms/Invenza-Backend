package com.chariitha.authbackend.controller;

import com.chariitha.authbackend.model.User;
import com.chariitha.authbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    //  Signup API
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        System.out.println("Signup request received for: " + user.getEmail());

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists with this email!");
        }

        userRepository.save(user);
        return ResponseEntity.ok("Signup successful!");
    }
     @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    System.out.println("Login request received for username: " + user.getUsername());

    Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

    if (existingUser.isEmpty()) {
        return ResponseEntity.badRequest().body("User not found! Please sign up first.");
    }

    User dbUser = existingUser.get();

    if (!dbUser.getPassword().equals(user.getPassword())) {
        return ResponseEntity.badRequest().body("Invalid password!");
    }

    return ResponseEntity.ok("Login successful!");
}

    // Test API (for checking backend is working)
    @GetMapping("/test")
    public String test() {
        return "Hello from Spring Boot Backend1 ";
    }
}



