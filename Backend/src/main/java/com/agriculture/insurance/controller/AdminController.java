package com.agriculture.insurance.controller;

import com.agriculture.insurance.entity.Insurance;
import com.agriculture.insurance.entity.User;
import com.agriculture.insurance.entity.Application;
import com.agriculture.insurance.entity.Feedback;
import com.agriculture.insurance.service.InsuranceService;
import com.agriculture.insurance.service.UserService;
import com.agriculture.insurance.service.ApplicationService;
import com.agriculture.insurance.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private FeedbackService feedbackService;

    // User Management
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
// ------------------ INSURANCE ---------------------

    @PostMapping("/insurance/add")
    public ResponseEntity<?> addInsurance(@RequestBody Insurance insurance) {
        try {
            Insurance savedInsurance = insuranceService.addInsurance(insurance);
            return ResponseEntity.ok(Map.of(
                    "message", "Insurance added successfully",
                    "insurance", savedInsurance
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/insurances")
    public ResponseEntity<List<Insurance>> getInsurances() {
        return ResponseEntity.ok(insuranceService.getAllInsurances());
    }

    @PutMapping("/insurance/{id}")
    public ResponseEntity<?> updateInsurance(@PathVariable Long id, @RequestBody Insurance insurance) {
        try {
            Insurance updated = insuranceService.updateInsurance(id, insurance);
            return ResponseEntity.ok(Map.of("message", "Updated successfully", "insurance", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/insurance/{id}")
    public ResponseEntity<?> deleteInsurance(@PathVariable Long id) {
        try {
            insuranceService.deleteInsurance(id);
            return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Officer Management
    @PostMapping("/officer/add")
    public ResponseEntity<?> addOfficer(@RequestBody User officer) {
        officer.setRole("OFFICER");
        try {
            User savedOfficer = userService.registerUser(officer);
            return ResponseEntity.ok(Map.of("message", "Officer added successfully", "officer", savedOfficer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/officers")
    public ResponseEntity<List<User>> getOfficers() {
        List<User> officers = userService.getAllUsers().stream()
                .filter(user -> "OFFICER".equals(user.getRole()))
                .toList();
        return ResponseEntity.ok(officers);
    }

    @DeleteMapping("/officer/{id}")
    public ResponseEntity<?> deleteOfficer(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of("message", "Officer deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Application Management
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        try {
            applicationService.deleteApplication(id);
            return ResponseEntity.ok(Map.of("message", "Application deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Feedback Management
    @GetMapping("/feedback")
    public ResponseEntity<List<Feedback>> getFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        try {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok(Map.of("message", "Feedback deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // Admin Login/Register (similar to user)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User admin) {
        admin.setRole("ADMIN");
        try {
            User savedAdmin = userService.registerUser(admin);
            return ResponseEntity.ok(Map.of("message", "Admin registered successfully", "admin", savedAdmin));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> user = userService.authenticateUser(email, password);
        if (user.isPresent() && "ADMIN".equals(user.get().getRole())) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "adminId", user.get().getId()));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid credentials"));
        }
    }
}
