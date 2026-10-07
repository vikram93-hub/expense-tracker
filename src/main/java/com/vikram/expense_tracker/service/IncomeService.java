package com.vikram.expense_tracker.service;

import com.vikram.expense_tracker.dto.IncomeRequest;
import com.vikram.expense_tracker.dto.IncomeResponse;
import com.vikram.expense_tracker.entity.Income;
import com.vikram.expense_tracker.entity.User;
import com.vikram.expense_tracker.repository.IncomeRepository;
import com.vikram.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public IncomeService(
            IncomeRepository incomeRepository,
            UserRepository userRepository) {

        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    public IncomeResponse createIncome(
            IncomeRequest request,
            String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Income income = new Income();

        income.setAmount(request.getAmount());
        income.setDate(request.getDate());
        income.setSource(request.getSource());
        income.setOwner(user);

        Income savedIncome = incomeRepository.save(income);

        return new IncomeResponse(
                savedIncome.getId(),
                savedIncome.getAmount(),
                savedIncome.getDate(),
                savedIncome.getSource()
        );
    }

    public List<IncomeResponse> getMyIncome(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return incomeRepository.findByOwner(user)
                .stream()
                .map(income ->
                        new IncomeResponse(
                                income.getId(),
                                income.getAmount(),
                                income.getDate(),
                                income.getSource()
                        ))
                .toList();
    }
}