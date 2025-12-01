package com.agriculture.insurance.repository;

import com.agriculture.insurance.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserId(Long userId);
    List<Application> findByStatus(String status);
}
