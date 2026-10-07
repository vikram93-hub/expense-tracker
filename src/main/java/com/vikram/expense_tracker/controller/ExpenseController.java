package com.vikram.expense_tracker.controller;

import com.vikram.expense_tracker.dto.ExpenseRequest;
import com.vikram.expense_tracker.dto.ExpenseResponse;
import com.vikram.expense_tracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@Valid
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody ExpenseRequest request,
            Authentication authentication) {

        String username = authentication.getName();

        ExpenseResponse response =
                expenseService.createExpense(request, username);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getMyExpenses(
            Authentication authentication) {

        String username = authentication.getName();

        List<ExpenseResponse> expenses =
                expenseService.getMyExpenses(username);

        return ResponseEntity.ok(expenses);
    }
}