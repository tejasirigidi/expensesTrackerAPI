package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.model.Expenses;
import com.example.expenseTrackerApi.model.Users;
import com.example.expenseTrackerApi.repo.UserRepo;
import com.example.expenseTrackerApi.repo.ExpensesRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @org.springframework.transaction.annotation.Transactional
    public Users userSignUp(Users request) {
        // make sure expenses list exists and has FK set

        if(userRepo.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already in use");
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Expenses> expenses = new ArrayList<>();
        if(request.getExpenses() != null) {

            for(Expenses exp : request.getExpenses()) {
                Expenses e = new Expenses();
                e.setUser(user);
                e.setCategory(exp.getCategory());
                e.setAmount(exp.getAmount());
                e.setNote(exp.getNote());
                expenses.add(e);
            }

        }
        user.setExpenses(expenses);
        return userRepo.save(user); // Cascade inserts expenses
    }

    public Users findUser(Users user) {
        return userRepo.findByUsername(user.getUsername());
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }
}
