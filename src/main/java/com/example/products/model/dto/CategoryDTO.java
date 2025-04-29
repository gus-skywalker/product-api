package com.example.products.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String fullPath;

}

