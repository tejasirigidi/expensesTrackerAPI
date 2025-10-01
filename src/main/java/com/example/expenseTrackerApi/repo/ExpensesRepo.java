package com.example.expenseTrackerApi.repo;

import com.example.expenseTrackerApi.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Integer> {
}
