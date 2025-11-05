package com.larbi.aitelhadj.spring_boot_3_security_jwt.config;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.service.CustomerUserDetailsService;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration for the application.
 * Defines beans for password encoding, authentication management, and HTTP security.
 * Integrates JWT authentication via {@link JwtAuthenticationFilter}.
 *
 * @author larbi.aitelhadj
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig {

    private final CustomerUserDetailsService customerUserDetailsService;
    private final JwtService jwtService;

    /**
     * Password encoder bean using BCrypt.
     *
     * @return a {@link PasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication manager bean configured with custom user details service and password encoder.
     *
     * @param http the HTTP security configuration
     * @param passwordEncoder the password encoder
     * @return an {@link AuthenticationManager} instance
     * @throws Exception if configuration fails
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * Security filter chain configuration.
     * Disables CSRF, permits unauthenticated access to "/api/auth/**",
     * requires authentication for all other requests, and adds the JWT filter.
     *
     * @param http the HTTP security configuration
     * @return a {@link SecurityFilterChain} instance
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(customerUserDetailsService, jwtService),
                        UsernamePasswordAuthenticationFilter.class).build();
    }

}
