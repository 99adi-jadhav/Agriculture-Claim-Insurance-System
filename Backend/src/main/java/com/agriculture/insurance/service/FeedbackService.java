package com.agriculture.insurance.service;

import com.agriculture.insurance.entity.Feedback;
import com.agriculture.insurance.entity.User;
import com.agriculture.insurance.repository.FeedbackRepository;
import com.agriculture.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public Feedback submitFeedback(Long userId, Feedback feedback) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        feedback.setUser(user);
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
