package com.vikram.expense_tracker.dto;

import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private Double amount;
    private Double gst;
    private LocalDate date;
    private Long categoryId;
    private String categoryName;

    public ExpenseResponse(
            Long id,
            Double amount,
            Double gst,
            LocalDate date,
            Long categoryId,
            String categoryName) {

        this.id = id;
        this.amount = amount;
        this.gst = gst;
        this.date = date;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getGst() {
        return gst;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}