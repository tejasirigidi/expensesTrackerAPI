package com.example.expenseTrackerApi.web.dto;

import jakarta.validation.constraints.*;

public record SignupRequest(@Email @NotBlank String email, @Size(min=8, max=72) String password) {
}
