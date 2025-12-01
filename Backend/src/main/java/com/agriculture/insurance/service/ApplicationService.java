package com.agriculture.insurance.service;

import com.agriculture.insurance.entity.Application;
import com.agriculture.insurance.entity.User;
import com.agriculture.insurance.repository.ApplicationRepository;
import com.agriculture.insurance.repository.UserRepository;
import com.agriculture.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    public Application submitApplication(Long userId, Long insuranceId, Application application) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        application.setUser(user);
        application.setInsurance(insuranceRepository.findById(insuranceId).orElseThrow(() -> new RuntimeException("Insurance not found")));
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<Application> getPendingApplications() {
        return applicationRepository.findByStatus("PENDING");
    }

    public Application approveApplication(Long id, Long officerId) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        User officer = userRepository.findById(officerId).orElseThrow(() -> new RuntimeException("Officer not found"));
        application.setStatus("APPROVED");
        application.setProcessedAt(LocalDateTime.now());
        application.setProcessedBy(officer);
        return applicationRepository.save(application);
    }

    public Application rejectApplication(Long id, Long officerId) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        User officer = userRepository.findById(officerId).orElseThrow(() -> new RuntimeException("Officer not found"));
        application.setStatus("REJECTED");
        application.setProcessedAt(LocalDateTime.now());
        application.setProcessedBy(officer);
        return applicationRepository.save(application);
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
