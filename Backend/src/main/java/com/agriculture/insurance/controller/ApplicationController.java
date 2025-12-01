package com.agriculture.insurance.controller;

import com.agriculture.insurance.entity.Application;
import com.agriculture.insurance.entity.Insurance;
import com.agriculture.insurance.entity.Feedback;
import com.agriculture.insurance.service.ApplicationService;
import com.agriculture.insurance.service.InsuranceService;
import com.agriculture.insurance.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private InsuranceService insuranceService;

    public static class ApplicationRequest {
        private Long userId;
        private Long insuranceId;
        private String farmerName;
        private String farmLocation;
        private Double farmSize;
        private String cropType;

        // Getters and setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Long getInsuranceId() { return insuranceId; }
        public void setInsuranceId(Long insuranceId) { this.insuranceId = insuranceId; }
        public String getFarmerName() { return farmerName; }
        public void setFarmerName(String farmerName) { this.farmerName = farmerName; }
        public String getFarmLocation() { return farmLocation; }
        public void setFarmLocation(String farmLocation) { this.farmLocation = farmLocation; }
        public Double getFarmSize() { return farmSize; }
        public void setFarmSize(Double farmSize) { this.farmSize = farmSize; }
        public String getCropType() { return cropType; }
        public void setCropType(String cropType) { this.cropType = cropType; }
    }

    @GetMapping("/insurances")
    public ResponseEntity<List<Insurance>> getInsurances() {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        return ResponseEntity.ok(insurances);
    }

    @PostMapping("/application/submit")
    public ResponseEntity<?> submitApplication(@RequestBody ApplicationRequest request) {
        try {
            // Validate required fields
            if (request.getUserId() == null || request.getInsuranceId() == null ||
                request.getFarmerName() == null || request.getFarmLocation() == null ||
                request.getFarmSize() == null || request.getCropType() == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "All fields are required"));
            }

            Long userId = request.getUserId();
            Long insuranceId = request.getInsuranceId();
            String farmerName = request.getFarmerName();
            String farmLocation = request.getFarmLocation();
            Double farmSize = request.getFarmSize();
            String cropType = request.getCropType();

            Application application = new Application();
            application.setFarmerName(farmerName);
            application.setFarmLocation(farmLocation);
            application.setFarmSize(java.math.BigDecimal.valueOf(farmSize));
            application.setCropType(cropType);

            Application savedApplication = applicationService.submitApplication(userId, insuranceId, application);
            return ResponseEntity.ok(Map.of("message", "Application submitted successfully", "application", savedApplication));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getUserApplications(@RequestParam Long userId) {
        List<Application> applications = applicationService.getApplicationsByUser(userId);
        return ResponseEntity.ok(applications);
    }

    @PostMapping("/feedback/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody Map<String, Object> request) {
        try {
            Long userId = Long.valueOf(request.get("userId").toString());
            String feedbackText = (String) request.get("feedback");
            Feedback feedback = feedbackService.submitFeedback(userId, feedbackText);
            return ResponseEntity.ok(Map.of("message", "Feedback submitted successfully", "feedback", feedback));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
