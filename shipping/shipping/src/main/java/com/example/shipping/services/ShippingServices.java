package com.example.shipping.services;
import  com.example.shipping.shipping.ShipmentStatus;
import com.example.shipping.repository.ShippingRepository;
import com.example.shipping.shipping.ShipmentStatus;
import com.example.shipping.shipping.Shipping;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ShippingServices {
    private final ShippingRepository shippingRepo;
    @Autowired
    public ShippingServices(ShippingRepository shippingRepo) {
        this.shippingRepo = shippingRepo;
    }

    public  List<Shipping> shipmentByUserID(long userID) {
        return shippingRepo.findShippingsByIdUser(userID);
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
    public void takeOrder(Shipping shipment){
        shipment.setStatus(ShipmentStatus.Pending);
        shippingRepo.save(shipment);
    }
}
