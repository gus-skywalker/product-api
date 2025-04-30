package com.example.products.model.dto;

import com.example.products.model.Product;
import lombok.Getter;

@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private boolean available;
    private String categoryPath;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.available = product.isAvailable();
        this.categoryPath = product.getCategoryPath();
    }

}
