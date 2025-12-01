package com.agriculture.insurance.controller;

import com.agriculture.insurance.entity.User;
import com.agriculture.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(Map.of("message", "User registered successfully", "userId", savedUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> user = userService.authenticateUser(email, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "userId", user.get().getId()));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid credentials"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUser(@RequestParam Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
