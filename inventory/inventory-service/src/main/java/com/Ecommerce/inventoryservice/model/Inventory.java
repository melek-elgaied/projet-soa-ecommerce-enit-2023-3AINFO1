package com.Ecommerce.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name ="t_inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column (name="reserved_quantity")
    private int reservedQuantity;

}
