package com.enit.domain;

import java.util.UUID;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    @Id
    private UUID productId;
    private Integer quantity;

    public Order() { }

}

