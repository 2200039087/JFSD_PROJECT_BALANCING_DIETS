package com.jfsd.Nutri_Solutions_backend.Controller;

import com.jfsd.Nutri_Solutions_backend.Model.Register;
import com.jfsd.Nutri_Solutions_backend.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "*") // Enable Cross-Origin Resource Sharing (CORS) if needed
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * Register a new user.
     * 
     * @param register the user details
     * @return the registered user
     */
    @PostMapping
    public ResponseEntity<Register> registerUser(@RequestBody Register register) {
        Register savedUser = registerService.saveUser(register);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Get user by email.
     * 
     * @param email the user's email
     * @return the user details if found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Register> getUserByEmail(@PathVariable String email) {
        Optional<Register> user = registerService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get all registered users.
     * 
     * @return the list of all users
     */
    @GetMapping
    public ResponseEntity<List<Register>> getAllUsers() {
        List<Register> users = registerService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get user by ID.
     * 
     * @param id the user's ID
     * @return the user details if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Register> getUserById(@PathVariable Long id) {
        Optional<Register> user = registerService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update user details.
     * 
     * @param id the ID of the user to update
     * @param updatedUser the updated user details
     * @return the updated user details
     */
    @PutMapping("/{id}")
    public ResponseEntity<Register> updateUser(@PathVariable Long id, @RequestBody Register updatedUser) {
        Optional<Register> updated = registerService.updateUser(id, updatedUser);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete user by ID.
     * 
     * @param id the user's ID
     * @return a confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<Register> user = registerService.findById(id);
        if (user.isPresent()) {
            registerService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Search for users by username.
     * 
     * @param username the substring of the username
     * @return the list of matching users
     */
    @GetMapping("/search/{username}")
    public ResponseEntity<List<Register>> searchUsersByUsername(@PathVariable String username) {
        List<Register> users = registerService.findUsersByUsername(username);
        return ResponseEntity.ok(users);
    }
}
