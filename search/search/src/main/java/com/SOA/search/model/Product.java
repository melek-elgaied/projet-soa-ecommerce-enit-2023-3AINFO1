package com.SOA.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@AllArgsConstructor
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    private Long id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Text, name = "description")
    private String description;
    @Field(type = FieldType.Text, name = "price")
    private double price;

    public Product(Long id, String description){
        if (id == null || description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Id and description cannot be null or empty.");
        }
        this.id = id;
        this.description = description;
    };

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    public Product() {
    }

}
