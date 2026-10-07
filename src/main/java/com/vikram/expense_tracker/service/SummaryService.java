package com.vikram.expense_tracker.service;

import com.vikram.expense_tracker.dto.SummaryResponse;
import com.vikram.expense_tracker.entity.Expense;
import com.vikram.expense_tracker.entity.Income;
import com.vikram.expense_tracker.entity.User;
import com.vikram.expense_tracker.repository.ExpenseRepository;
import com.vikram.expense_tracker.repository.IncomeRepository;
import com.vikram.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SummaryService {

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public SummaryService(
            UserRepository userRepository,
            ExpenseRepository expenseRepository,
            IncomeRepository incomeRepository) {

        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public SummaryResponse getMonthlySummary(
            String username,
            int year,
            int month) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(
                startDate.lengthOfMonth());

        List<Income> incomes =
                incomeRepository.findByOwnerAndDateBetween(
                        user,
                        startDate,
                        endDate);

        List<Expense> expenses =
                expenseRepository.findByOwnerAndDateBetween(
                        user,
                        startDate,
                        endDate);

        double totalIncome = incomes.stream()
                .mapToDouble(Income::getAmount)
                .sum();

        double totalExpense = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double balance = totalIncome - totalExpense;

        return new SummaryResponse(
                totalIncome,
                totalExpense,
                balance
        );
    }
}