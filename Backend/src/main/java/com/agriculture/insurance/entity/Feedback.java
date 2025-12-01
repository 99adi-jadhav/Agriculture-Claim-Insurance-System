package com.agriculture.insurance.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 1000)
    private String message;

    @Column(nullable = false)
    private Integer rating; // 1-5

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt = LocalDateTime.now();
}
