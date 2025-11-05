package com.larbi.aitelhadj.spring_boot_3_security_jwt.repository;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * Provides CRUD operations and a method to find users by username.
 * Extends {@link JpaRepository} for standard database access.
 *
 * @author larbi.aitelhadj
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an {@link Optional} containing the user if found, or empty otherwise
     */
    Optional<User> findByUsername(String username);

}
