package com.larbi.aitelhadj.spring_boot_3_security_jwt.service;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link JwtService}.
 * Verifies JWT generation, validation, and claim extraction.
 *
 * @author larbi.aitelhadj
 */
@SpringBootTest
class JwtServiceTest {

    @BeforeEach
    void setUp() {
        String secretKey = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
        Integer expirationTime = 3600000;
        jwtService = new JwtService();
        TestUtils.setField(jwtService, "keySecret", secretKey);
        TestUtils.setField(jwtService, "expirationTime", expirationTime);
    }

    @Autowired
    private JwtService jwtService;

    @Test
    void generateToken() {
        String username = "user1";
        String token = jwtService.generateToken(username);
        assertNotNull(token);
        assertEquals("user1", jwtService.extractUsername(token));
    }

    @Test
    void isTokenValid() {
        String username = "user1";
        UserDetails userDetails = User.withUsername(username).password("password").roles("role").build();
        String token = jwtService.generateToken(username);
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void extractUsername() {
        String username = "user1";
        String token = jwtService.generateToken(username);
        assertEquals(username, jwtService.extractUsername(token));
    }

    @Test
    void extractClaim() {
        String username = "user1";
        String token = jwtService.generateToken(username);
        String extractUsername = jwtService.extractClaim(token, Claims::getSubject);
        assertEquals(username, extractUsername);
    }

    static class TestUtils {
        static void setField(Object target, String fieldName, Object value) {
            try {
                var field = target.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(target, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}