package com.jfsd.Nutri_Solutions_backend.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String nutrientIntake; // Detailed nutrient intake

    @Column(nullable = false)
    private Long userId; // User ID

    @ElementCollection
    @CollectionTable(name = "analysis_deficiencies", joinColumns = @JoinColumn(name = "analysis_result_id"))
    @Column(name = "deficiency")
    private List<String> deficiencies; // List of identified deficiencies

    @Column(length = 1000)
    private String symptomsAnalysis; // Analysis of symptoms related to deficiencies

    @Column(length = 1000)
    private String recommendations; // Suggestions or action plans

    public AnalysisResult() {
    }

    public AnalysisResult(String nutrientIntake, Long userId, List<String> deficiencies, String symptomsAnalysis, String recommendations) {
        this.nutrientIntake = nutrientIntake;
        this.userId = userId;
        this.deficiencies = deficiencies;
        this.symptomsAnalysis = symptomsAnalysis;
        this.recommendations = recommendations;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNutrientIntake() {
        return nutrientIntake;
    }

    public void setNutrientIntake(String nutrientIntake) {
        this.nutrientIntake = nutrientIntake;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getDeficiencies() {
        return deficiencies;
    }

    public void setDeficiencies(List<String> deficiencies) {
        this.deficiencies = deficiencies;
    }

    public String getSymptomsAnalysis() {
        return symptomsAnalysis;
    }

    public void setSymptomsAnalysis(String symptomsAnalysis) {
        this.symptomsAnalysis = symptomsAnalysis;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public String toString() {
        return "AnalysisResult{" +
                "id=" + id +
                ", nutrientIntake='" + nutrientIntake + '\'' +
                ", userId=" + userId +
                ", deficiencies=" + deficiencies +
                ", symptomsAnalysis='" + symptomsAnalysis + '\'' +
                ", recommendations='" + recommendations + '\'' +
                '}';
    }
}
