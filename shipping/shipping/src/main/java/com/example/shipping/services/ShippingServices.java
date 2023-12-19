package com.example.shipping.services;
import  com.example.shipping.shipping.ShipmentStatus;
import com.example.shipping.repository.ShippingRepository;
import com.example.shipping.shipping.ShipmentStatus;
import com.example.shipping.shipping.Shipping;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public void startShipment(Long IdShipping) {
        Optional<Shipping> optionalShipment = shippingRepo.findByIdShipping(IdShipping);

        if (optionalShipment.isPresent()) {
            Shipping shipment = optionalShipment.get();
            shipment.setStatus(ShipmentStatus.Shipping);

            shippingRepo.save(shipment);


        } else {

            throw new EntityNotFoundException("Shipment with IdShipping " + IdShipping + " not found");
        }
    }
    public Shipping updateShipmentStatus(Long idShipping, ShipmentStatus status){

    }
}
