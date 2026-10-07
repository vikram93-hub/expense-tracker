package com.vikram.expense_tracker.service;

import com.vikram.expense_tracker.dto.CategoryRequest;
import com.vikram.expense_tracker.dto.CategoryResponse;
import com.vikram.expense_tracker.entity.Category;
import com.vikram.expense_tracker.entity.User;
import com.vikram.expense_tracker.repository.CategoryRepository;
import com.vikram.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public CategoryResponse createCategory(
            CategoryRequest request,
            String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Category category = new Category();
        category.setName(request.getName());
        category.setOwner(user);

        Category savedCategory = categoryRepository.save(category);

        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName()
        );
    }

    public List<CategoryResponse> getMyCategories(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return categoryRepository.findByOwner(user)
                .stream()
                .map(category ->
                        new CategoryResponse(
                                category.getId(),
                                category.getName()
                        ))
                .toList();
    }
}