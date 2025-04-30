package com.example.products.service;

import com.example.products.model.dto.CategoryCreateRequest;
import com.example.products.model.dto.CategoryDTO;
import com.example.products.model.Category;
import com.example.products.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(cat -> new CategoryDTO(
                        cat.getId(),
                        cat.getName(),
                        cat.getFullPath()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Category createCategory(CategoryCreateRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        return categoryRepository.save(category);
    }

}
