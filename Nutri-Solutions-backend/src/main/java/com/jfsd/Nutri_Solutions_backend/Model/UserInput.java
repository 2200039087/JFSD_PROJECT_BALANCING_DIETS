package com.jfsd.Nutri_Solutions_backend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class UserInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long userId; // Link to the user ID for personalization

    @NotNull
    @Size(min = 3, max = 50) // Username should be between 3 and 50 characters
    @Column(nullable = false)
    private String username;

    @NotNull
    @Size(min = 1, max = 500) // Limiting inputData to a maximum of 500 characters
    @Column(nullable = false, length = 500)
    private String inputData; // User's input data, e.g., dietary preferences

    @Column(nullable = true)
    private String additionalNotes; // Optional additional notes

    public UserInput() {
    }

    public UserInput(Long userId, String username, String inputData, String additionalNotes) {
        this.userId = userId;
        this.username = username;
        this.inputData = inputData;
        this.additionalNotes = additionalNotes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
