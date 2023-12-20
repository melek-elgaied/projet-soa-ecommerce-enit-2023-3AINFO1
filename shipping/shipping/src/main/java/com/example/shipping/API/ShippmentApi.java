package com.example.shipping.API;

import com.example.shipping.services.ShippingServices;
import com.example.shipping.shipping.ShipmentStatus;
import com.example.shipping.shipping.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shippment")
public class ShippmentApi {
    private ShippingServices shippingServices;

    @Autowired
    public  ShippmentApi(ShippingServices shippingService)
    {
        this.shippingServices=shippingService;
    }

    @GetMapping("/shippment/{userid}")
    public ResponseEntity<List<Shipping>> getShippementsByIdUser(long userID) {
        List<Shipping> shippings = shippingServices.shipmentByUserID(userID);
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

    @PostMapping("/startshippemnt")
    public ResponseEntity<Shipping> startShippementAPI(Long idShippment)
    {
        shippingServices.startShipment(idShippment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/takeOrder")
    public ResponseEntity<Shipping> takeOrderToShippmentAPI(Shipping shipping)
    {
        shippingServices.takeOrder(shipping);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/deliverShippment")
    public ResponseEntity<Shipping> deliverShippmentAPI(Long idShipping)
    {
        shippingServices.deliverShipment(idShipping);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
