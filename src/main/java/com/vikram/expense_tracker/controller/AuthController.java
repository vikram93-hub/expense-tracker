package com.vikram.expense_tracker.controller;

import com.vikram.expense_tracker.dto.LoginRequest;
import com.vikram.expense_tracker.dto.LoginResponse;
import com.vikram.expense_tracker.dto.RegisterRequest;
import com.vikram.expense_tracker.dto.UserResponse;
import com.vikram.expense_tracker.entity.User;
import com.vikram.expense_tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vikram.expense_tracker.dto.LoginRequest;
import com.vikram.expense_tracker.dto.LoginResponse;
import jakarta.validation.Valid;

@Valid
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}
