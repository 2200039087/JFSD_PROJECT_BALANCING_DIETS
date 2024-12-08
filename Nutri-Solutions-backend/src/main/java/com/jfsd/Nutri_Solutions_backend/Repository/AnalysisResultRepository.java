package com.jfsd.Nutri_Solutions_backend.Repository;

import com.jfsd.Nutri_Solutions_backend.Model.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {

    // Find all results by user ID
    List<AnalysisResult> findAllByUserId(Long userId);

    // Find results by a specific keyword in the analysis summary
    List<AnalysisResult> findBySymptomsAnalysisContaining(String keyword);

    // Find results by nutrient deficiency or surplus
    @Query("SELECT ar FROM AnalysisResult ar JOIN ar.deficiencies d WHERE d = :deficiency")
    List<AnalysisResult> findByDeficienciesContaining(@Param("deficiency") String deficiency);
}
