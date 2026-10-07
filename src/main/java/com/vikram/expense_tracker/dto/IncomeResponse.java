package com.vikram.expense_tracker.dto;

import java.time.LocalDate;

public class IncomeResponse {

    private Long id;
    private Double amount;
    private LocalDate date;
    private String source;

    public IncomeResponse(
            Long id,
            Double amount,
            LocalDate date,
            String source) {

        this.id = id;
        this.amount = amount;
        this.date = date;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }
}