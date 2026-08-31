package com.example.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class JwtGenerator {

    private final SecretKey key;
    private final JwtParser jwtParser;
    private final JwtProperties jwtProperties;

    public JwtGenerator(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        this.jwtParser = Jwts.parser().verifyWith(key).build();
    }

    public String generateToken(Authentication authentication, String userType) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtProperties.getExpiration());

        log.info("Generating JWT token for user: {}, with userType: {}", username, userType);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expiryDate)
                .signWith(key)
                .claim("usertype", userType)
                .compact();

        log.info("JWT token generated successfully for user: {}", username);

        return token;
    }

    public String getUsernameFromJWT(String token) {
        log.info("Extracting username from JWT token");
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();
        String username = claims.getSubject();
        log.info("Username extracted from JWT token: {}", username);
        return username;
    }

    public String getUserTypeFromJWT(String token) {
        log.info("Extracting user type from JWT token");
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();
        String userType = claims.get("usertype").toString();
        log.info("User type extracted from JWT token: {}", userType);
        return userType;
    }

    public boolean validateToken(String token) {
        log.info("Validating JWT token");
        try {
            jwtParser.parseSignedClaims(token);
            log.info("JWT token is valid");
            return true;
        } catch (Exception ex) {
            log.error("JWT token validation failed: {}", token, ex);
            throw new AuthenticationCredentialsNotFoundException("JWT token is not valid: " + token, ex);
        }
    }
}