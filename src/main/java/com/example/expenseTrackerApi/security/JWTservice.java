package com.example.expenseTrackerApi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.time.Instant;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {
    private final Key key;
    private final long expirySeconds;

    public JWTservice(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expirySeconds}") long exp) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirySeconds = exp;
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();
        return Jwts.builder().setSubject(subject).addClaims(claims)
                .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(expirySeconds)))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parser()
                .setSigningKey(key).build().parseClaimsJws(token);
    }

    public long getExpirySeconds() {
        return expirySeconds;
    }

}
