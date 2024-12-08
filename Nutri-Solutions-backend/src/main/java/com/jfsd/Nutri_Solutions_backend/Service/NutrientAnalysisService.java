package com.jfsd.Nutri_Solutions_backend.Service;

import com.jfsd.Nutri_Solutions_backend.Model.AnalysisResult;
import com.jfsd.Nutri_Solutions_backend.Model.UserInput;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NutrientAnalysisService {

    public AnalysisResult analyzeUserInput(UserInput userInput) {
        // Placeholder for actual analysis logic
        String nutrientIntake = "Carbs: 60%, Protein: 20%, Fat: 20%";
        List<String> deficiencies = Arrays.asList("Vitamin D", "Iron");
        String symptomsAnalysis = "Fatigue might be related to Iron deficiency.";
        String recommendations = "Increase intake of leafy greens, fish, and nuts.";

        return new AnalysisResult(nutrientIntake, userInput.getUserId(), deficiencies, symptomsAnalysis, recommendations);
    }
}
