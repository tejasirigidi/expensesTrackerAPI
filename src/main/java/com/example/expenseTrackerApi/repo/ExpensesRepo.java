package com.example.expenseTrackerApi.repo;

import com.example.expenseTrackerApi.model.entity.Expenses;
import com.example.expenseTrackerApi.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Long> {
    @Query("""
    select e from Expenses e
    where e.user = :user and e.expenseDate between :start and :end
    order by e.expenseDate desc, e.id desc
  """)
    List<Expenses> findAllByUserAndDateBetween(@Param("user") Users user,
                                               @Param("start") LocalDate start,
                                               @Param("end") LocalDate end);

    Optional<Expenses> findByIdAndUser(Long id, Users user);
}
