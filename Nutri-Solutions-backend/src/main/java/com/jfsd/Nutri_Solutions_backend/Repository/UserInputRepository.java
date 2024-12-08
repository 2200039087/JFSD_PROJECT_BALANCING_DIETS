package com.jfsd.Nutri_Solutions_backend.Repository;

import com.jfsd.Nutri_Solutions_backend.Model.UserInput;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInputRepository extends JpaRepository<UserInput, Long> {

    // Custom query to find UserInput by a specific field, e.g., username
    UserInput findByUsername(String username);

    // Example query to find all entries for a specific user by ID
    List<UserInput> findAllByUserId(Long userId);

    // Query for filtering user inputs containing specific keywords
    List<UserInput> findByInputDataContaining(String keyword);
}
