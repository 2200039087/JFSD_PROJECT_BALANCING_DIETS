package com.jfsd.Nutri_Solutions_backend.Controller;

import com.jfsd.Nutri_Solutions_backend.Model.MealPreference;
import com.jfsd.Nutri_Solutions_backend.Service.MealPlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal-planner")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from the frontend running on localhost:3000
public class MealPlannerController {

    @Autowired
    private MealPlannerService mealPlannerService;

    @PostMapping("/preferences")
    public ResponseEntity<?> savePreferences(@RequestBody MealPreference preferences) {
        try {
            return ResponseEntity.ok(mealPlannerService.savePreferences(preferences));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving preferences");
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateMealPlan(@RequestBody MealPreference preferences) {
        try {
            // Call service to generate the meal plan
            return ResponseEntity.ok(mealPlannerService.generateMealPlan(preferences));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating meal plan");
        }
    }
}
