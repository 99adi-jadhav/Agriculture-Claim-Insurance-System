package com.agriculture.insurance.service;

import com.agriculture.insurance.entity.Insurance;
import com.agriculture.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    public Insurance addInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    public Optional<Insurance> getInsuranceById(Long id) {
        return insuranceRepository.findById(id);
    }

    public Insurance updateInsurance(Long id, Insurance insuranceDetails) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
        insurance.setName(insuranceDetails.getName());
        insurance.setDescription(insuranceDetails.getDescription());
        insurance.setPremium(insuranceDetails.getPremium());
        insurance.setCoverageAmount(insuranceDetails.getCoverageAmount());
        insurance.setCropType(insuranceDetails.getCropType());
        return insuranceRepository.save(insurance);
    }

    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }
}
