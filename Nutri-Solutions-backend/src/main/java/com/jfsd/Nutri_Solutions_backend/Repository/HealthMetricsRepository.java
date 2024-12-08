package com.jfsd.Nutri_Solutions_backend.Repository;

import com.jfsd.Nutri_Solutions_backend.Model.HealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HealthMetricsRepository extends JpaRepository<HealthMetrics, Long> {
    List<HealthMetrics> findByRecordDate(LocalDate recordDate);
}
