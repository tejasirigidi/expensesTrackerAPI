package com.example.expenseTrackerApi.web.dto.expenses;

import com.example.expenseTrackerApi.model.enums.ExpenseCategory;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        @NotNull @DecimalMin("0.01") BigDecimal amount,
        @NotNull ExpenseCategory category,
        @Size(max=255) String description,
        @NotNull LocalDate expenseDate
) {
}
