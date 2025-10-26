package com.example.expenseTrackerApi.web.controller;

import com.example.expenseTrackerApi.service.expensesService;
import com.example.expenseTrackerApi.web.dto.expenses.ExpenseRequest;
import com.example.expenseTrackerApi.web.dto.expenses.ExpenseResponse;
import com.example.expenseTrackerApi.web.mapper.ExpenseMapper;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class expensesController {

    private final expensesService service;

    public expensesController(expensesService s) {
        this.service = s;
    }

    @GetMapping
    public List<ExpenseResponse> list(
            @RequestParam(required = false) String range,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        LocalDate today = LocalDate.now();
        LocalDate start;
        LocalDate end;
        if (startDate != null && endDate != null) {
            start = startDate;
            end = endDate;
        } else if ("past_week".equalsIgnoreCase(range)) {
            end = today;
            start = today.minusDays(7);
        } else if ("past_month".equalsIgnoreCase(range)) {
            end = today;
            start = today.minusMonths(1);
        } else if ("last_3_months".equalsIgnoreCase(range)) {
            end = today;
            start = today.minusMonths(3);
        } else {
            end = today;
            start = today.minusMonths(1);
        } // default

        return service.list(start, end).stream().map(ExpenseMapper::toDto).toList();
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody ExpenseRequest req) {
        return ResponseEntity.ok(ExpenseMapper.toDto(service.create(req)));
    }

    @PutMapping("/{id}")
    public ExpenseResponse update(@PathVariable Long id, @Valid @RequestBody ExpenseRequest req) {
        return ExpenseMapper.toDto(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
