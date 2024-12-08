package com.jfsd.Nutri_Solutions_backend.Service;

import com.jfsd.Nutri_Solutions_backend.Model.MealPreference;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MealPlannerService {

    public MealPreference savePreferences(MealPreference preferences) {
        // Save the preferences logic, maybe to a database
        // For now, just return the preferences back
        return preferences;
    }

    public Object generateMealPlan(MealPreference preferences) {
        // Generate meal plan based on preferences (for now mock data)
        return generateMockMealPlan();
    }

    private Map<String, Map<String, String>> generateMockMealPlan() {
        // Create a map to store meal plans for each day
        Map<String, Map<String, String>> mealPlan = new HashMap<>();

        // Monday's meal plan
        Map<String, String> mondayMeals = new HashMap<>();
        mondayMeals.put("Breakfast", "Oatmeal");
        mondayMeals.put("Lunch", "Grilled Chicken");
        mondayMeals.put("Dinner", "Salmon");
        mealPlan.put("Monday", mondayMeals);

        // Tuesday's meal plan
        Map<String, String> tuesdayMeals = new HashMap<>();
        tuesdayMeals.put("Breakfast", "Eggs");
        tuesdayMeals.put("Lunch", "Salad");
        tuesdayMeals.put("Dinner", "Pasta");
        mealPlan.put("Tuesday", tuesdayMeals);

        // Wednesday's meal plan
        Map<String, String> wednesdayMeals = new HashMap<>();
        wednesdayMeals.put("Breakfast", "Smoothie");
        wednesdayMeals.put("Lunch", "Turkey Sandwich");
        wednesdayMeals.put("Dinner", "Tofu Stir Fry");
        mealPlan.put("Wednesday", wednesdayMeals);

        // Return the full meal plan
        return mealPlan;
    }
}
