package com.Ecommerce.inventoryservice.controller;

import com.Ecommerce.inventoryservice.model.Inventory;
import com.Ecommerce.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("productName") String productName) {
        try {
            boolean isAvailable = inventoryService.isInStock(productName);
            return ResponseEntity.ok(isAvailable);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> allInventory = inventoryService.getAllInventory();
        return new ResponseEntity<>(allInventory, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory){
        Inventory newInventory=  inventoryService.addInventory(inventory);
        return  new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory){
        Inventory updateInventory=  inventoryService.updateInventory(inventory);
        return  new ResponseEntity<>(updateInventory, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable ("id") Long id){
        inventoryService.deleteIventory(id);
        return  new ResponseEntity<>( HttpStatus.OK);
    }


}
