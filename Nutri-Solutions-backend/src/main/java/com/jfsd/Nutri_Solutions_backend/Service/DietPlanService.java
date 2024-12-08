package com.jfsd.Nutri_Solutions_backend.Service;

import com.jfsd.Nutri_Solutions_backend.Model.DietPlan;
import com.jfsd.Nutri_Solutions_backend.Repository.DietPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietPlanService {

    private final DietPlanRepository dietPlanRepository;

    public DietPlanService(DietPlanRepository dietPlanRepository) {
        this.dietPlanRepository = dietPlanRepository;
    }

    public DietPlan saveDietPlan(DietPlan dietPlan) {
        return dietPlanRepository.save(dietPlan);
    }

    public List<DietPlan> getAllDietPlans() {
        return dietPlanRepository.findAll();
    }

    public DietPlan getDietPlanById(Long id) {
        return dietPlanRepository.findById(id).orElse(null);
    }

    public void deleteDietPlan(Long id) {
        dietPlanRepository.deleteById(id);
    }

    public List<DietPlan> findByMacroDistribution(Integer carbs, Integer protein, Integer fat) {
        return dietPlanRepository.findByMacroDistribution(carbs, protein, fat);
    }
}
