package com.jfsd.Nutri_Solutions_backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goal;
    private String dietaryPreference;
    private String allergies;
    private Integer mealsPerDay;
    private String mealTiming;
    private String foodFrequency;
    private Integer calorieIntake; // Change this to Integer
    private Integer carbs;
    private Integer proteins;
    private Integer fats;
    private String cookingPref;
    private String kitchenEquip;
    private String mealPrepTime;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Integer getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(Integer mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public String getMealTiming() {
        return mealTiming;
    }

    public void setMealTiming(String mealTiming) {
        this.mealTiming = mealTiming;
    }

    public String getFoodFrequency() {
        return foodFrequency;
    }

    public void setFoodFrequency(String foodFrequency) {
        this.foodFrequency = foodFrequency;
    }

    public Integer getCalorieIntake() {
        return calorieIntake;
    }

    public void setCalorieIntake(Integer calorieIntake) {
        this.calorieIntake = calorieIntake;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public String getCookingPref() {
        return cookingPref;
    }

    public void setCookingPref(String cookingPref) {
        this.cookingPref = cookingPref;
    }

    public String getKitchenEquip() {
        return kitchenEquip;
    }

    public void setKitchenEquip(String kitchenEquip) {
        this.kitchenEquip = kitchenEquip;
    }

    public String getMealPrepTime() {
        return mealPrepTime;
    }

    public void setMealPrepTime(String mealPrepTime) {
        this.mealPrepTime = mealPrepTime;
    }
}
