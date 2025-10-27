package com.chariitha.authbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chariitha.authbackend.model.User;
import com.chariitha.authbackend.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5174") // frontend React
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        System.out.println("👉 Signup attempt for: " + user.getEmail());

        boolean exists = userRepository.existsByEmail(user.getEmail());
        System.out.println("🔍 Email exists in DB? " + exists);

        if (exists) {
            System.out.println("❌ Email already exists in DB: " + user.getEmail());
            return "Email already exists!";
        }

        userRepository.save(user);
        System.out.println("✅ Signup successful for: " + user.getEmail());

        return "Signup successful!";
    }
}
