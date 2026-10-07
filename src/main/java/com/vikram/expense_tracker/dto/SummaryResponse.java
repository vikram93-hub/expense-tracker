package com.vikram.expense_tracker.dto;

public class SummaryResponse {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;

    public SummaryResponse(
            Double totalIncome,
            Double totalExpense,
            Double balance) {

        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public Double getBalance() {
        return balance;
    }
}