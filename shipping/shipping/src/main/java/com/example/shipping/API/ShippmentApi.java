package com.example.shipping.API;

import com.example.shipping.services.ShippingServices;
import com.example.shipping.shipping.ShipmentStatus;
import com.example.shipping.shipping.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shippment")
public class ShippmentApi {
    private ShippingServices shippingServices;

    @Autowired
    public  ShippmentApi(ShippingServices shippingService)
    {
        this.shippingServices=shippingService;
    }

    @PostMapping("/startshippemnt")
    public ResponseEntity<Shipping> startShippementAPI(Shipping shipping,long userID)
    {
        Shipping newShipping = shippingServices.startShipment(shipping);

        return new ResponseEntity<>(newShipping, HttpStatus.CREATED);

    }

    @PutMapping("/updateShippemnt")
    public ResponseEntity<Shipping> updateShippementAPI(Long idShipping, ShipmentStatus status)
    {
        Shipping updateShipping = shippingServices.updateShipmentStatus(idShipping,status);
        shippingServices.shipmentByUserID(userID);
        return new ResponseEntity<>(updateShipping, HttpStatus.OK);
    }



}
