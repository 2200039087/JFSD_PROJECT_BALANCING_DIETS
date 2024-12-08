package com.jfsd.Nutri_Solutions_backend.Repository;

import com.jfsd.Nutri_Solutions_backend.Model.HealthGuide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthGuideRepository extends JpaRepository<HealthGuide, Long> {
    List<HealthGuide> findByConditionContainingIgnoreCase(String condition);
}
