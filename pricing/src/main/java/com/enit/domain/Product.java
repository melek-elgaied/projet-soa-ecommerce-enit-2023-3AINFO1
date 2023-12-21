package com.enit.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Product {
    @Id
    UUID productId;
    double price;

}

