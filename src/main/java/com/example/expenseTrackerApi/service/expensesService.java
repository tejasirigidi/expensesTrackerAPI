package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.model.entity.Expenses;
import com.example.expenseTrackerApi.model.entity.Users;
import com.example.expenseTrackerApi.repo.ExpensesRepo;
import com.example.expenseTrackerApi.repo.UserRepo;
import com.example.expenseTrackerApi.web.dto.expenses.ExpenseRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class expensesService {
    private final ExpensesRepo repo;
    private final UserRepo users;

    public expensesService(ExpensesRepo r, UserRepo u) {
        this.repo = r;
        this.users = u;
    }

    private Users currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return users.findByEmail(auth.getName()).orElseThrow();
    }

    public List<Expenses> list(LocalDate start, LocalDate end) {
        return repo.findAllByUserAndDateBetween(currentUser(), start, end);
    }

    @Transactional
    public Expenses create(ExpenseRequest req) {
        var e = new Expenses();
        e.setUser(currentUser());
        e.setAmount(req.amount());
        e.setCategory(req.category());
        e.setDescription(req.description());
        e.setExpenseDate(req.expenseDate());
        return repo.save(e);
    }

    @Transactional
    public Expenses update(Long id, ExpenseRequest req) {
        var e = repo.findByIdAndUser(id, currentUser())
                .orElseThrow(() -> new IllegalArgumentException("Expense not found"));
        e.setAmount(req.amount());
        e.setCategory(req.category());
        e.setDescription(req.description());
        e.setExpenseDate(req.expenseDate());
        return repo.save(e);
    }

    @Transactional
    public void delete(Long id) {
        var e = repo.findByIdAndUser(id, currentUser())
                .orElseThrow(() -> new IllegalArgumentException("Expense not found"));
        repo.delete(e);
    }
}
