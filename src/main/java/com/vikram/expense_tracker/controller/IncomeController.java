package com.vikram.expense_tracker.controller;

import com.vikram.expense_tracker.dto.IncomeRequest;
import com.vikram.expense_tracker.dto.IncomeResponse;
import com.vikram.expense_tracker.service.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@Valid
@RestController
@RequestMapping("/api/income")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public ResponseEntity<IncomeResponse> createIncome(
            @Valid @RequestBody IncomeRequest request,
            Authentication authentication) {
        String username = authentication.getName();

        IncomeResponse response =
                incomeService.createIncome(request, username);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<IncomeResponse>> getMyIncome(
            Authentication authentication) {

        String username = authentication.getName();

        List<IncomeResponse> income =
                incomeService.getMyIncome(username);

        return ResponseEntity.ok(income);
    }
}