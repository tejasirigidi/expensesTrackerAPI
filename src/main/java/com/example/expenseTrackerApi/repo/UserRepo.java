package com.example.expenseTrackerApi.repo;

import com.example.expenseTrackerApi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);

    public Users findByUsername(String username);
}
