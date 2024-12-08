package com.jfsd.Nutri_Solutions_backend.Repository;

import com.jfsd.Nutri_Solutions_backend.Model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

    /**
     * Find a user by their email address.
     * 
     * @param email the email of the user
     * @return the Register entity
     */
    Register findByEmail(String email);

    /**
     * Find users whose username contains the specified string (case-insensitive).
     * 
     * @param username the substring of the username
     * @return a list of Register entities
     */
    List<Register> findByUsernameContainingIgnoreCase(String username);

    /**
     * Find users who have accepted the terms.
     * 
     * @return a list of Register entities
     */
    List<Register> findByTermsAcceptedTrue();

    /**
     * Find users born after a certain date.
     * 
     * @param dob the cutoff date of birth
     * @return a list of Register entities
     */
    List<Register> findByDobAfter(java.time.LocalDate dob);
}
