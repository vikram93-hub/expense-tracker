package com.vikram.expense_tracker.controller;

import com.vikram.expense_tracker.dto.CategoryRequest;
import com.vikram.expense_tracker.dto.CategoryResponse;
import com.vikram.expense_tracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@Valid
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
          @Valid @RequestBody CategoryRequest request,
            Authentication authentication) {

        String username = authentication.getName();

        CategoryResponse response =
                categoryService.createCategory(request, username);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getMyCategories(
            Authentication authentication) {

        String username = authentication.getName();

        List<CategoryResponse> categories =
                categoryService.getMyCategories(username);

        return ResponseEntity.ok(categories);
    }
}