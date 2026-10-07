package com.vikram.expense_tracker.repository;

import com.vikram.expense_tracker.entity.Income;
import com.vikram.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByOwner(User owner);

    List<Income> findByOwnerAndDateBetween(
            User owner,
            LocalDate startDate,
            LocalDate endDate);
}