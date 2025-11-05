package com.larbi.aitelhadj.spring_boot_3_security_jwt.controller;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.User;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.repository.UserRepository;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for user authentication and registration.
 * Provides endpoints to register new users and login existing users.
 * Uses {@link JwtService} to generate JWT tokens and {@link AuthenticationManager}
 * to validate user credentials.
 *
 * @author larbi.aitelhadj
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Slf4j
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user.
     * Checks if the username already exists. If not, encodes the password
     * and saves the user to the repository.
     *
     * @param user the {@link User} object with username and password
     * @return 200 OK with saved user, or 400 Bad Request if username exists
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    /**
     * Authenticates a user and returns a JWT token.
     * Validates credentials and generates a JWT if successful.
     *
     * @param user the {@link User} object with username and password.
     * @return 200 OK with JWT token if authenticated, or 401 Unauthorized otherwise.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtService.generateToken(user.getUsername()));
                authData.put("type", "Bearer");
                return ResponseEntity.ok(authData);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
