package com.jfsd.Nutri_Solutions_backend.Service;

import com.jfsd.Nutri_Solutions_backend.Model.HealthMetrics;
import com.jfsd.Nutri_Solutions_backend.Repository.HealthMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HealthMetricsService {

    @Autowired
    private HealthMetricsRepository healthMetricsRepository;

    public HealthMetrics saveMetrics(HealthMetrics metrics) {
        return healthMetricsRepository.save(metrics);
    }

    public List<HealthMetrics> getMetricsByDate(LocalDate date) {
        return healthMetricsRepository.findByRecordDate(date);
    }

    public List<HealthMetrics> getAllMetrics() {
        return healthMetricsRepository.findAll();
    }

    public void deleteMetrics(Long id) {
        healthMetricsRepository.deleteById(id);
    }

    public String analyzeHealth(HealthMetrics metrics) {
        double bmi = calculateBMI(metrics.getWeight(), metrics.getHeight());
        String bmiStatus = getBMIStatus(bmi);

        String bloodPressureStatus = getBloodPressureStatus(metrics.getBloodPressure().getSystolic(), metrics.getBloodPressure().getDiastolic());
        String bloodSugarStatus = getBloodSugarStatus(metrics.getBloodSugar());
        String cholesterolStatus = getCholesterolStatus(metrics.getCholesterol());

        String suggestions = getSuggestions(bmiStatus, bloodPressureStatus, bloodSugarStatus, cholesterolStatus);

        return suggestions;
    }

    private double calculateBMI(double weight, double height) {
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    private String getBMIStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private String getBloodPressureStatus(int systolic, int diastolic) {
        if (systolic < 120 && diastolic < 80) {
            return "Normal";
        } else if (systolic >= 120 && systolic < 140 && diastolic >= 80 && diastolic < 90) {
            return "Elevated";
        } else {
            return "High";
        }
    }

    private String getBloodSugarStatus(int bloodSugar) {
        if (bloodSugar < 100) {
            return "Normal";
        } else if (bloodSugar >= 100 && bloodSugar < 125) {
            return "Pre-diabetes";
        } else {
            return "Diabetes";
        }
    }

    private String getCholesterolStatus(int cholesterol) {
        if (cholesterol < 200) {
            return "Normal";
        } else if (cholesterol >= 200 && cholesterol < 239) {
            return "Borderline high";
        } else {
            return "High";
        }
    }

    private String getSuggestions(String bmiStatus, String bloodPressureStatus, String bloodSugarStatus, String cholesterolStatus) {
        StringBuilder suggestions = new StringBuilder();
        suggestions.append("Health Status:\n");
        suggestions.append("BMI: ").append(bmiStatus).append("\n");
        suggestions.append("Blood Pressure: ").append(bloodPressureStatus).append("\n");
        suggestions.append("Blood Sugar: ").append(bloodSugarStatus).append("\n");
        suggestions.append("Cholesterol: ").append(cholesterolStatus).append("\n");

        if (!bmiStatus.equals("Normal weight")) {
            suggestions.append("Suggestion: Maintain a balanced diet and exercise regularly to achieve a healthy weight.\n");
        }

        if (!bloodPressureStatus.equals("Normal")) {
            suggestions.append("Suggestion: Monitor your blood pressure regularly and consult a doctor if necessary.\n");
        }

        if (!bloodSugarStatus.equals("Normal")) {
            suggestions.append("Suggestion: Control your sugar intake and consult a doctor for further advice.\n");
        }

        if (!cholesterolStatus.equals("Normal")) {
            suggestions.append("Suggestion: Reduce intake of saturated fats and consult a doctor for further advice.\n");
        }

        return suggestions.toString();
    }
}
