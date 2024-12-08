package com.jfsd.Nutri_Solutions_backend.Controller;

import com.jfsd.Nutri_Solutions_backend.Model.AnalysisResult;
import com.jfsd.Nutri_Solutions_backend.Model.UserInput;
import com.jfsd.Nutri_Solutions_backend.Service.NutrientAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nutrient-analysis")
@CrossOrigin(origins = "http://localhost:3000")
public class NutrientAnalysisController {

    @Autowired
    private NutrientAnalysisService nutrientAnalysisService;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResult> analyzeUserInput(@RequestBody UserInput userInput) {
        AnalysisResult result = nutrientAnalysisService.analyzeUserInput(userInput);
        return ResponseEntity.ok(result);
    }
}
