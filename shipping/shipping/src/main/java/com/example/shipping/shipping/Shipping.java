package com.example.shipping.shipping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Entity
@AllArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long idShipping;

    private Long idUser;
    private String addressUser;
    private ShipmentStatus status;


    public Shipping(){
        this.status=ShipmentStatus.Shipping;
    }


    @jakarta.persistence.Id
    public Long getIdShipping() {
        return idShipping;
    }
}
