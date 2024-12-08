package com.jfsd.Nutri_Solutions_backend.Service;

import com.jfsd.Nutri_Solutions_backend.Model.HealthGuide;
import com.jfsd.Nutri_Solutions_backend.Model.Nutrient;
import com.jfsd.Nutri_Solutions_backend.Model.Recipe;
import com.jfsd.Nutri_Solutions_backend.Repository.HealthGuideRepository;
import com.jfsd.Nutri_Solutions_backend.Repository.NutrientRepository;
import com.jfsd.Nutri_Solutions_backend.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeBaseService {

    @Autowired
    private NutrientRepository nutrientRepository;

    @Autowired
    private HealthGuideRepository healthGuideRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Nutrient> searchNutrients(String query) {
        if (query == null || query.trim().isEmpty()) {
            return nutrientRepository.findAll();
        }
        return nutrientRepository.findByNameContainingIgnoreCase(query);
    }

    public List<HealthGuide> searchHealthGuides(String condition) {
        if (condition == null || condition.trim().isEmpty()) {
            return healthGuideRepository.findAll();
        }
        return healthGuideRepository.findByConditionContainingIgnoreCase(condition);
    }

    public List<Recipe> searchRecipes(String dietaryRestriction) {
        if (dietaryRestriction == null || dietaryRestriction.trim().isEmpty()) {
            return recipeRepository.findAll();
        }
        return recipeRepository.findByDietaryRestrictionContainingIgnoreCase(dietaryRestriction);
    }
}
