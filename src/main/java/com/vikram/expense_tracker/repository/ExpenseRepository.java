package com.vikram.expense_tracker.repository;

import com.vikram.expense_tracker.entity.Expense;
import com.vikram.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByOwner(User owner);

    List<Expense> findByOwnerAndDateBetween(
            User owner,
            LocalDate startDate,
            LocalDate endDate);
}