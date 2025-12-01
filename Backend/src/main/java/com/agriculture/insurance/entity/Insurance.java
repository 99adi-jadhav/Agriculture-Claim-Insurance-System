package com.agriculture.insurance.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "insurances")
@Data
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal premium;

    @Column(name = "coverage_amount", nullable = false)
    @JsonProperty("coverage_amount") // <---- FIXED
    private BigDecimal coverageAmount;

    @Column(name = "crop_type", nullable = false)
    @JsonProperty("crop_type") // <---- FIXED
    private String cropType;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
