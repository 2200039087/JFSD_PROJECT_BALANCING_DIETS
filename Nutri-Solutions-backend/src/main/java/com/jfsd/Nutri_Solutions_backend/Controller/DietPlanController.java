package com.jfsd.Nutri_Solutions_backend.Controller;

import org.springframework.web.bind.annotation.*;
import com.jfsd.Nutri_Solutions_backend.Model.DietPlan;
import com.jfsd.Nutri_Solutions_backend.Service.DietPlanService;

import java.util.List;

@RestController
@RequestMapping("/api/diet-plans")
@CrossOrigin(origins = "http://localhost:3000") // Adjust for your frontend URL
public class DietPlanController {

    private final DietPlanService dietPlanService;

    public DietPlanController(DietPlanService dietPlanService) {
        this.dietPlanService = dietPlanService;
    }

    @PostMapping
    public DietPlan createDietPlan(@RequestBody DietPlan dietPlan) {
        // Here you can add logic to generate a diet plan based on the input data
        // For now, we'll just save the diet plan and return it
        return dietPlanService.saveDietPlan(dietPlan);
    }

    @GetMapping
    public List<DietPlan> getAllDietPlans() {
        return dietPlanService.getAllDietPlans();
    }

    @GetMapping("/{id}")
    public DietPlan getDietPlanById(@PathVariable Long id) {
        return dietPlanService.getDietPlanById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDietPlan(@PathVariable Long id) {
        dietPlanService.deleteDietPlan(id);
    }

    @GetMapping("/macro")
    public List<DietPlan> getDietPlansByMacro(@RequestParam Integer carbs, @RequestParam Integer protein, @RequestParam Integer fat) {
        return dietPlanService.findByMacroDistribution(carbs, protein, fat);
    }
}
