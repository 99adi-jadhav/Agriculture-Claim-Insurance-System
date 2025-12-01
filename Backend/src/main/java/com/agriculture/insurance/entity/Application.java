package com.agriculture.insurance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    private Insurance insurance;

    @Column(nullable = false)
    private String farmerName;

    @Column(nullable = false)
    private String farmLocation;

    @Column(nullable = false)
    private BigDecimal farmSize;

    @Column(nullable = false)
    private String cropType;

    @Column(nullable = false)
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED

    @Column(name = "applied_at")
    private LocalDateTime appliedAt = LocalDateTime.now();

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @ManyToOne
    @JoinColumn(name = "processed_by")
    private User processedBy;
}
