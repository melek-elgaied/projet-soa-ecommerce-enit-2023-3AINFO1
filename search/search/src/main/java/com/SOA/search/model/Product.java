package com.SOA.search.model;

import lombok.Getter;
@Getter
public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;

    public Product(Long id, String description){
        if (id == null || description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Id and description cannot be null or empty.");
        }
        this.id = id;
        this.description = description;
    };

}
