package com.chariitha.authbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthBackendApplication.class, args);
        System.out.println("✅ Spring Boot Backend Running on http://localhost:8080");
    }
}
