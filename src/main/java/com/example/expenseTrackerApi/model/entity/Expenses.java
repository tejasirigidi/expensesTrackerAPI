package com.example.expenseTrackerApi.model.entity;

import com.example.expenseTrackerApi.model.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "expenses")
public class Expenses {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false, fetch=FetchType.LAZY) private Users user;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal amount;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private ExpenseCategory category;
    private String description;
    @Column(nullable=false) private LocalDate expenseDate;
}