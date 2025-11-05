package com.larbi.aitelhadj.spring_boot_3_security_jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service for generating and validating JSON Web Tokens (JWT).
 * Provides methods to create tokens, validate them, and extract claims such as the username.
 * The service uses a secret key and expiration time defined in the application properties.
 *
 * @author larbi.aitelhadj
 */
@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String keySecret;

    @Value("${jwt.expiration-time}")
    private Integer expirationTime;

    /**
     * Generates a JWT token for the given username.
     *
     * @param username the username to include in the token's subject
     * @return a signed JWT token as a String
     */
    public String generateToken(String username) {
        Map<String, String> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] bytes = keySecret.getBytes();
        return new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * Checks if the given token is valid for the provided user details.
     *
     * @param token the JWT token
     * @param userDetails the user's details to validate against
     * @return true if the token is valid and not expired; false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    /**
     * Extracts a specific claim from the JWT token.
     *
     * @param <T> the type of the claim
     * @param token the JWT token
     * @param claimsResolve a function to extract a specific claim from the token's claims
     * @return the value of the requested claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolve) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolve.apply(claims);
    }

}
