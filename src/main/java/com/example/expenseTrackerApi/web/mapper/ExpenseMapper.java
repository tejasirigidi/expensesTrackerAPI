package com.example.expenseTrackerApi.web.mapper;

import com.example.expenseTrackerApi.model.entity.Expenses;
import com.example.expenseTrackerApi.web.dto.expenses.ExpenseResponse;

public class ExpenseMapper {
    public static ExpenseResponse toDto(Expenses e){
        return new ExpenseResponse(e.getId(), e.getAmount(), e.getCategory(), e.getDescription(), e.getExpenseDate());
    }
}
