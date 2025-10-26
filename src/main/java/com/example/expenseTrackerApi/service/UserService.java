package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.model.entity.Expenses;
import com.example.expenseTrackerApi.model.entity.Users;
import com.example.expenseTrackerApi.repo.UserRepo;

import com.example.expenseTrackerApi.security.JWTservice;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final JWTservice jwtservice;

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo userRepo, JWTservice jwtservice, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtservice = jwtservice;
        this.authenticationManager = authenticationManager;
    }

    @org.springframework.transaction.annotation.Transactional
    public Users userSignUp(Users request) {
        // make sure expenses list exists and has FK set

        if (userRepo.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already in use");
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Expenses> expenses = new ArrayList<>();
        if (request.getExpenses() != null) {

            for (Expenses exp : request.getExpenses()) {
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

    public String findUser(Users user) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtservice.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }
}
