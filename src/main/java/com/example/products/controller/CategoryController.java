package com.example.products.controller;

import com.example.products.model.Category;
import com.example.products.model.dto.CategoryCreateRequest;
import com.example.products.model.dto.CategoryDTO;
import com.example.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreateRequest request) {
        Category category = categoryService.createCategory(request);
        return ResponseEntity.ok(new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getFullPath()
        ));
    }


}

