package com.example.backend.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {

    @Value("${jwt.expiration:1000000}")
    private long expiration;

    @Value("${jwt.secret}")
    private String secret;
}
