package com.jfsd.Nutri_Solutions_backend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class MealPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String allergies; // Allergies (e.g., nuts, dairy, etc.)

    @Column(nullable = false, length = 100)
    private String cuisine; // Preferred cuisine type (e.g., Italian, Indian)

    @Column(nullable = false, length = 100)
    private String mealType; // Type of meal (e.g., breakfast, lunch, dinner)

    @Min(0)
    @Column(nullable = false)
    private int calories; // Calories for the meal

    @Min(0)
    @Column(nullable = false)
    private int carbs; // Carbohydrates (grams)

    @Min(0)
    @Column(nullable = false)
    private int proteins; // Proteins (grams)

    @Min(0)
    @Column(nullable = false)
    private int fats; // Fats (grams)

    @Column(nullable = false, length = 255)
    private String dietaryRestriction; // Dietary restriction (e.g., vegetarian, vegan)

    @Column(nullable = false)
    private Long userId; // User ID

    @Column(nullable = false, length = 255)
    private String preferredMeals; // Preferred meals

    public MealPreference() {
    }

    public MealPreference(String allergies, String cuisine, String mealType, int calories, int carbs, int proteins, int fats, String dietaryRestriction, Long userId, String preferredMeals) {
        this.allergies = allergies;
        this.cuisine = cuisine;
        this.mealType = mealType;
        this.calories = calories;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        this.dietaryRestriction = dietaryRestriction;
        this.userId = userId;
        this.preferredMeals = preferredMeals;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public String getDietaryRestriction() {
        return dietaryRestriction;
    }

    public void setDietaryRestriction(String dietaryRestriction) {
        this.dietaryRestriction = dietaryRestriction;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPreferredMeals() {
        return preferredMeals;
    }

    public void setPreferredMeals(String preferredMeals) {
        this.preferredMeals = preferredMeals;
    }
}
