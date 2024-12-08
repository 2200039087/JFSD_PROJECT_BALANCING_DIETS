package com.jfsd.Nutri_Solutions_backend.Service;

import com.jfsd.Nutri_Solutions_backend.Model.Register;
import com.jfsd.Nutri_Solutions_backend.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    /**
     * Save a new user to the database.
     * 
     * @param register the Register entity to be saved
     * @return the saved Register entity
     */
    public Register saveUser(Register register) {
        return registerRepository.save(register);
    }

    /**
     * Find a user by their email address.
     * 
     * @param email the email of the user
     * @return an Optional containing the Register entity, if found
     */
    public Optional<Register> findByEmail(String email) {
        return Optional.ofNullable(registerRepository.findByEmail(email));
    }

    /**
     * Retrieve all registered users.
     * 
     * @return a list of all Register entities
     */
    public List<Register> getAllUsers() {
        return registerRepository.findAll();
    }

    /**
     * Find a user by their ID.
     * 
     * @param id the ID of the user
     * @return an Optional containing the Register entity, if found
     */
    public Optional<Register> findById(Long id) {
        return registerRepository.findById(id);
    }

    /**
     * Update an existing user's details.
     * 
     * @param id the ID of the user to update
     * @param updatedUser the updated Register entity
     * @return the updated Register entity, if found and updated
     */
    public Optional<Register> updateUser(Long id, Register updatedUser) {
        return registerRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setDob(updatedUser.getDob());
            existingUser.setTermsAccepted(updatedUser.isTermsAccepted());
            return registerRepository.save(existingUser);
        });
    }

    /**
     * Delete a user by their ID.
     * 
     * @param id the ID of the user to delete
     */
    public void deleteUser(Long id) {
        registerRepository.deleteById(id);
    }

    /**
     * Find users whose username contains a specified string (case-insensitive).
     * 
     * @param username the substring of the username
     * @return a list of Register entities matching the criteria
     */
    public List<Register> findUsersByUsername(String username) {
        return registerRepository.findByUsernameContainingIgnoreCase(username);
    }
}
