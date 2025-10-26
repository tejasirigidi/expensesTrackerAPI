package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.model.entity.Users;
import com.example.expenseTrackerApi.repo.UserRepo;
import com.example.expenseTrackerApi.security.JWTservice;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final UserRepo users;
    private final PasswordEncoder encoder;
    private final JWTservice jwt;
    private final AuthenticationManager authManager;

    public AuthService(UserRepo u, PasswordEncoder e, JWTservice j, AuthenticationManager am) {
        this.users = u;
        this.encoder = e;
        this.jwt = j;
        this.authManager = am;
    }

    public String signup(String email, String rawPassword) {
        if (users.existsByEmail(email)) throw new IllegalArgumentException("Email already in use");
        var u = new Users();
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(rawPassword));
        users.save(u);
        return jwt.generateToken(u.getEmail(), Map.of("uid", u.getId()));
    }

    public String login(String email, String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return jwt.generateToken(email, Map.of());
    }

    public long expirySeconds() {
        return jwt.getExpirySeconds();
    }
}
