package com.agriculture.insurance.controller;

import com.agriculture.insurance.entity.Application;
import com.agriculture.insurance.entity.User;
import com.agriculture.insurance.service.ApplicationService;
import com.agriculture.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/officer")
@CrossOrigin(origins = "*")
public class OfficerController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User officer) {
        officer.setRole("OFFICER");
        try {
            User savedOfficer = userService.registerUser(officer);
            return ResponseEntity.ok(Map.of("message", "Officer registered successfully", "officerId", savedOfficer.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> user = userService.authenticateUser(email, password);
        if (user.isPresent() && "OFFICER".equals(user.get().getRole())) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "officerId", user.get().getId()));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid credentials"));
        }
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getApplications() {
        List<Application> applications = applicationService.getPendingApplications();
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/applications/{id}/approve")
    public ResponseEntity<?> approveApplication(@PathVariable Long id) {
        try {
            applicationService.approveApplication(id);
            return ResponseEntity.ok(Map.of("message", "Application approved"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/applications/{id}/reject")
    public ResponseEntity<?> rejectApplication(@PathVariable Long id) {
        try {
            applicationService.rejectApplication(id);
            return ResponseEntity.ok(Map.of("message", "Application rejected"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
