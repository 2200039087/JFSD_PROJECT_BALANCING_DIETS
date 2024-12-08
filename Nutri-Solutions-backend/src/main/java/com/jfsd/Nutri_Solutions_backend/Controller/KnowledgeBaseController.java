package com.jfsd.Nutri_Solutions_backend.Controller;

import com.jfsd.Nutri_Solutions_backend.Model.HealthGuide;
import com.jfsd.Nutri_Solutions_backend.Model.Nutrient;
import com.jfsd.Nutri_Solutions_backend.Model.Recipe;
import com.jfsd.Nutri_Solutions_backend.Service.KnowledgeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-base")
@CrossOrigin(origins = "http://localhost:3000")
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @PostMapping("/search")
    public ResponseEntity<Object> searchKnowledgeBase(@RequestBody SearchRequest searchRequest) {
        try {
            System.out.println("Received search query: " + searchRequest.getSearchQuery());

            List<Nutrient> nutrients = knowledgeBaseService.searchNutrients(searchRequest.getSearchQuery());
            List<HealthGuide> healthGuides = knowledgeBaseService.searchHealthGuides(searchRequest.getHealthCondition());
            List<Recipe> recipes = knowledgeBaseService.searchRecipes(searchRequest.getDietaryRestriction());

            System.out.println("Found Nutrients: " + nutrients.size());

            return ResponseEntity.ok(new KnowledgeBaseResponse(nutrients, healthGuides, recipes));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while processing your request: " + e.getMessage());
        }
    }
}

class SearchRequest {
    private String searchQuery;
    private String dietaryRestriction;
    private String healthCondition;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getDietaryRestriction() {
        return dietaryRestriction;
    }

    public void setDietaryRestriction(String dietaryRestriction) {
        this.dietaryRestriction = dietaryRestriction;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }
}

class KnowledgeBaseResponse {
    private List<Nutrient> nutrients;
    private List<HealthGuide> healthGuides;
    private List<Recipe> recipes;

    public KnowledgeBaseResponse(List<Nutrient> nutrients, List<HealthGuide> healthGuides, List<Recipe> recipes) {
        this.nutrients = nutrients;
        this.healthGuides = healthGuides;
        this.recipes = recipes;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public List<HealthGuide> getHealthGuides() {
        return healthGuides;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
