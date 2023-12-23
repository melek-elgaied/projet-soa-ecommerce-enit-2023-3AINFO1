package com.enit.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@AllArgsConstructor
@Getter
public class ProductPrice {
    @Id
    private UUID productId;
    private double productPrice;

    public ProductPrice() {
    }
}

