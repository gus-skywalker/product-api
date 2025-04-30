package com.example.products.service;

import com.example.products.model.Category;
import com.example.products.model.Product;
import com.example.products.repository.CategoryRepository;
import com.example.products.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product createProduct(Product product) {
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);
        product.setCategoryPath(category.getFullPath());

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product updated) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(updated.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setAvailable(updated.isAvailable());
        existing.setCategory(category);
        existing.setCategoryPath(category.getFullPath());

        return productRepository.save(existing);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public Page<Product> findFiltered(Optional<String> name,
                                         Optional<Long> categoryId,
                                         Optional<Boolean> available,
                                         Pageable pageable) {

        Specification<Product> spec = Specification.where(null);

        if (name.isPresent()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.get().toLowerCase() + "%"));
        }

        if (categoryId.isPresent()) {
            Set<Long> ids = this.getDescendantCategoryIds(categoryId.get());
            spec = spec.and((root, query, cb) -> {
                Join<Product, Category> categoryJoin = root.join("category");
                return categoryJoin.get("id").in(ids);
            });

        }

        if (available.isPresent()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("available"), available.get()));
        }

        return productRepository.findAll(spec, pageable);
    }

    public Set<Long> getDescendantCategoryIds(Long categoryId) {
        Set<Long> result = new HashSet<>();
        Category root = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        collectDescendants(root, result);
        return result;
    }

    private void collectDescendants(Category category, Set<Long> result) {
        result.add(category.getId());
        for (Category child : category.getChildren()) {
            collectDescendants(child, result);
        }
    }

}

