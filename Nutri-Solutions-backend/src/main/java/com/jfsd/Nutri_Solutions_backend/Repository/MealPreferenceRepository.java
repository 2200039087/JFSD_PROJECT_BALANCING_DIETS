package com.jfsd.Nutri_Solutions_backend.Repository;

import com.jfsd.Nutri_Solutions_backend.Model.MealPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPreferenceRepository extends JpaRepository<MealPreference, Long> {

    // Custom query to find preferences by dietary restriction
    List<MealPreference> findByDietaryRestriction(String dietaryRestriction);

    // Custom query to find preferences based on user ID or other criteria
    List<MealPreference> findByUserId(Long userId);

    // Example query to find preferences containing a specific keyword in meals
    List<MealPreference> findByPreferredMealsContaining(String keyword);
}
