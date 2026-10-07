package com.vikram.expense_tracker.controller;

import com.vikram.expense_tracker.dto.SummaryResponse;
import com.vikram.expense_tracker.service.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping
    public ResponseEntity<SummaryResponse> getMonthlySummary(
            @RequestParam int year,
            @RequestParam int month,
            Authentication authentication) {

        String username = authentication.getName();

        SummaryResponse response =
                summaryService.getMonthlySummary(
                        username,
                        year,
                        month);

        return ResponseEntity.ok(response);
    }
}