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
    public  void updateShipmentStatus(Long id, ShipmentStatus shipmentStatus){
        Optional<Shipping> optionalShipment = shippingRepo.findByIdShipping(id);

        if (optionalShipment.isPresent()) {
            Shipping shipment = optionalShipment.get();
            shipment.setStatus(shipmentStatus);

            shippingRepo.save(shipment);


        } else {

            throw new EntityNotFoundException("Shipment with IdShipping " + id + " not found");
        }
    }
    private final ShippingRepository shippingRepo;
    @Autowired
    public ShippingServices(ShippingRepository shippingRepo) {
        this.shippingRepo = shippingRepo;
    }

    public  List<Shipping> shipmentByUserID(long userID) {
        return shippingRepo.findShippingsByIdUser(userID);
    }
    public void startShipment(Long IdShipping) {
        updateShipmentStatus(IdShipping,ShipmentStatus.Shipping);

    }
    public void deliverShipment(Long idShipping) {
        updateShipmentStatus(idShipping,ShipmentStatus.Delivered);
    }
    public void takeOrder(Shipping shipping){
        shipping.setStatus(ShipmentStatus.Pending);
        shippingRepo.save(shipping);
    }
    public String AddressByShippmentID (Long idShipping) {
        Optional<Shipping> optionalShipment = shippingRepo.findByIdShipping(id);

        if (optionalShipment.isPresent()) {
            Shipping shipment = optionalShipment.get();
            return shipment.address;
        }
        else {
            throw new EntityNotFoundException("Shipment with IdShipping " + id + " not found");
        }

    }
}
