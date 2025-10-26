package com.example.expenseTrackerApi.web.controller;

import com.example.expenseTrackerApi.service.AuthService;
import com.example.expenseTrackerApi.web.dto.JwtResponse;
import com.example.expenseTrackerApi.web.dto.LoginRequest;
import com.example.expenseTrackerApi.web.dto.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;

    public AuthController(AuthService a) {
        this.auth = a;
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> signup(@Valid @RequestBody SignupRequest req) {
        var token = auth.signup(req.email(), req.password());
        return ResponseEntity.ok(new JwtResponse(token, auth.expirySeconds()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req) {
        var token = auth.login(req.email(), req.password());
        return ResponseEntity.ok(new JwtResponse(token, auth.expirySeconds()));
    }
}
