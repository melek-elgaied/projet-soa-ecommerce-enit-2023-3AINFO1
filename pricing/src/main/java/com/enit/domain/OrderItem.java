package com.enit.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderItem {
    private UUID idProduct;
    private int quantity;
}
