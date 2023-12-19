package com.example.shipping.services;

import com.example.shipping.repository.ShippingRepository;
import com.example.shipping.shipping.ShipmentStatus;
import com.example.shipping.shipping.Shipping;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class ShippingServices {
    private final ShippingRepository shippingRepo;
    @Autowired
    public ShippingServices(ShippingRepository shippingRepo) {
        this.shippingRepo = shippingRepo;
    }

    public static void shipmentByUserID(long userID) {

    }
    public Shipping startShipment(Shipping shipping){



    }
    public Shipping updateShipmentStatus(Long idShipping, ShipmentStatus status){

    }
}
