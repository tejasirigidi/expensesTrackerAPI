package com.example.expenseTrackerApi.web.dto.expenses;

import com.example.expenseTrackerApi.model.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(Long id, BigDecimal amount, ExpenseCategory category, String description, LocalDate expenseDate)  {
}
