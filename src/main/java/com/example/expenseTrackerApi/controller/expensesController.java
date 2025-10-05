package com.example.expenseTrackerApi.controller;

import com.example.expenseTrackerApi.model.Expenses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class expensesController {

    @PostMapping
    public void postExpenses(@RequestBody Expenses expenses){

    }


}
