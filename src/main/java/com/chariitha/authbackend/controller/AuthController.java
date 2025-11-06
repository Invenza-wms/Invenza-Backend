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
    @PostMapping("/signup") // creates an API endpoint that handles POST requests from frontend
    public ResponseEntity<?> signup(@RequestBody User user) {
        System.out.println("Signup request received for: " + user.getEmail());
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    if (!user.getEmail().matches(emailRegex)) {
        return ResponseEntity.badRequest().body("Invalid email format!");
    }      //Send error message to frontend.

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists with this email!");
        }

        userRepository.save(user); //if user is new it saves users details in database
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

    
    @GetMapping("/test")
    public String test() {
        return "Hello from Spring Boot Backend1 ";
    }
}




