package com.jfsd.Nutri_Solutions_backend.Controller;

import com.jfsd.Nutri_Solutions_backend.Model.HealthMetrics;
import com.jfsd.Nutri_Solutions_backend.Service.HealthMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Adjust for your frontend URL
public class HealthMetricsController {

    @Autowired
    private HealthMetricsService healthMetricsService;

    @PostMapping("/health-metrics")
    public HealthMetrics saveHealthMetrics(@RequestBody HealthMetrics healthMetrics) {
        return healthMetricsService.saveMetrics(healthMetrics);
    }

    @GetMapping("/{date}")
    public List<HealthMetrics> getMetricsByDate(@PathVariable String date) {
        return healthMetricsService.getMetricsByDate(LocalDate.parse(date));
    }

    @GetMapping("/health-metrics")
    public List<HealthMetrics> getAllMetrics() {
        return healthMetricsService.getAllMetrics();
    }

    @DeleteMapping("/{id}")
    public void deleteMetrics(@PathVariable Long id) {
        healthMetricsService.deleteMetrics(id);
    }

    @PostMapping("/analyze")
    public String analyzeHealth(@RequestBody HealthMetrics healthMetrics) {
        return healthMetricsService.analyzeHealth(healthMetrics);
    }
}
