package com.example.shipping.shipping;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    @Id
    private Long idShipping;
    private Long idUser;
    private String addressUser;
    private ShipmentStatus status;


    public void setIdShipping(Long idShipping) {
        this.idShipping = idShipping;
    }

    @jakarta.persistence.Id
    public Long getIdShipping() {
        return idShipping;
    }
}
