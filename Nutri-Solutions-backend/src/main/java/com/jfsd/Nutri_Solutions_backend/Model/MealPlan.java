package com.jfsd.Nutri_Solutions_backend.Model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String day;

    @ElementCollection
    @CollectionTable(name = "meal_plan_meals", joinColumns = @JoinColumn(name = "meal_plan_id"))
    @MapKeyColumn(name = "meal_type") // This will store the key (meal type)
    @Column(name = "meal_description") // This will store the value (meal description)
    private Map<String, String> meals; // Maps mealType to meal description

    @Column(nullable = false)
    private int totalCalories;

    @Column(nullable = false)
    private String macronutrients;

    public MealPlan() {
    }

    public MealPlan(String day, Map<String, String> meals, int totalCalories, String macronutrients) {
        this.day = day;
        this.meals = meals;
        this.totalCalories = totalCalories;
        this.macronutrients = macronutrients;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Map<String, String> getMeals() {
        return meals;
    }

    public void setMeals(Map<String, String> meals) {
        this.meals = meals;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public String getMacronutrients() {
        return macronutrients;
    }

    public void setMacronutrients(String macronutrients) {
        this.macronutrients = macronutrients;
    }
}
