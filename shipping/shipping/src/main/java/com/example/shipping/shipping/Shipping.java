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

    public void setIdShipping(Long idShipping) {
        this.idShipping = idShipping;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }
}
