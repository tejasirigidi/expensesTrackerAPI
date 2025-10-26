package com.example.expenseTrackerApi.web.dto;

public record JwtResponse(String token, long expiresInSeconds) {
}
