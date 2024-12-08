package com.jfsd.Nutri_Solutions_backend.Repository;

import com.jfsd.Nutri_Solutions_backend.Model.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {

    // Find diet plans by dietary preference
    List<DietPlan> findByDietaryPreference(String dietaryPreference);

    // Find diet plans for a specific goal
    List<DietPlan> findByGoal(String goal);

    // Find diet plans with calorie intake less than a specified value
    List<DietPlan> findByCalorieIntakeLessThan(Integer calorieIntake);

    // Find diet plans with meals per day within a specified range
    List<DietPlan> findByMealsPerDayBetween(Integer minMeals, Integer maxMeals);

    // Custom query to find diet plans matching a specific macro distribution
    @Query("SELECT d FROM DietPlan d WHERE d.carbs = :carbs AND d.proteins = :protein AND d.fats = :fat")
    List<DietPlan> findByMacroDistribution(@Param("carbs") Integer carbs, @Param("protein") Integer protein, @Param("fat") Integer fat);

    // Find diet plans with a specific cooking preference
    List<DietPlan> findByCookingPref(String cookingPref);

    // Find diet plans with a specific meal preparation time
    List<DietPlan> findByMealPrepTime(String mealPrepTime);
}
