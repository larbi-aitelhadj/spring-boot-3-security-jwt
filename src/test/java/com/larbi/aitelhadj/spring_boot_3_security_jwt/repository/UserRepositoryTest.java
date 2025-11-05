package com.larbi.aitelhadj.spring_boot_3_security_jwt.repository;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.User;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

/**
 * Unit tests for {@link UserRepository} using an in-memory test database.
 * This class tests basic CRUD operations:
 *     - findAll() – verifies all users are returned correctly.
 *     - save() – verifies a new user is saved successfully.
 *     - findById() – verifies a user can be retrieved by ID.
 *     - deleteById() – verifies a user can be deleted by ID.
 * The tests use the {@code data-test.sql} script to prepopulate test data.
 *
 * @author larbi.aitelhadj
 */
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/data-test.sql")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAll() {
        List<User> userList = userRepository.findAll();
        assertThat(userList).size().isEqualTo(2);
        User userFirst = userList.getFirst();
        assertEquals("user1", userFirst.getUsername());
        assertEquals("password1", userFirst.getPassword());
        assertEquals("role_admin", userFirst.getRole());
    }

    @Test
    void testSave() {
        User user = new User(null, "user3", "password3", "role_admin");
        User userSaved = userRepository.save(user);
        assertThat(userSaved).isNotNull();
        assertEquals(3L, userSaved.getId());
        assertEquals("user3", userSaved.getUsername());
        assertEquals("password3", userSaved.getPassword());
        assertEquals("role_admin", userSaved.getRole());
    }

    @Test
    void testFindById() {
        User userFinded = userRepository.findById(1L).orElse(null);
        assertThat(userFinded).isNotNull();
        assertEquals(1L, userFinded.getId());
        assertEquals("user1", userFinded.getUsername());
        assertEquals("password1", userFinded.getPassword());
        assertEquals("role_admin", userFinded.getRole());
    }

    @Test
    void testDeleteById() {
        User userFirst = userRepository.findById(1L).orElse(null);
        assertThat(userFirst).isNotNull();
        userRepository.deleteById(1L);
        User userFirstDeleted = userRepository.findById(1L).orElse(null);
        assertThat(userFirstDeleted).isNull();
    }
}