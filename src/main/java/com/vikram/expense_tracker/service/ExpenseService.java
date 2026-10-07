package com.vikram.expense_tracker.service;

import com.vikram.expense_tracker.dto.ExpenseRequest;
import com.vikram.expense_tracker.dto.ExpenseResponse;
import com.vikram.expense_tracker.entity.Category;
import com.vikram.expense_tracker.entity.Expense;
import com.vikram.expense_tracker.entity.User;
import com.vikram.expense_tracker.repository.CategoryRepository;
import com.vikram.expense_tracker.repository.ExpenseRepository;
import com.vikram.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository) {

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public ExpenseResponse createExpense(
            ExpenseRequest request,
            String username) {

        // Find logged-in user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Find category
        Category category = categoryRepository
                .findByIdAndOwner(request.getCategoryId(), user)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        // Create expense
        Expense expense = new Expense();

        expense.setAmount(request.getAmount());
        expense.setGst(request.getGst());
        expense.setDate(request.getDate());
        expense.setCategory(category);
        expense.setOwner(user);

        Expense savedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getAmount(),
                savedExpense.getGst(),
                savedExpense.getDate(),
                savedExpense.getCategory().getId(),
                savedExpense.getCategory().getName()
        );
    }

    public List<ExpenseResponse> getMyExpenses(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return expenseRepository.findByOwner(user)
                .stream()
                .map(expense ->
                        new ExpenseResponse(
                                expense.getId(),
                                expense.getAmount(),
                                expense.getGst(),
                                expense.getDate(),
                                expense.getCategory().getId(),
                                expense.getCategory().getName()
                        ))
                .toList();
    }
}