package com.Ecommerce.inventoryservice.service;

import com.Ecommerce.inventoryservice.model.Inventory;
import com.Ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
@Transactional(readOnly = true)
    public boolean isInStock(String productName){
       return inventoryRepository.findByproductName(productName).isPresent();

    }
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }
    public Inventory addInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }
    public List<Inventory> findAllInventories(){
        return inventoryRepository.findAll();
    }
    public Inventory updateInventory(Inventory inventory){
        return  inventoryRepository.save(inventory);
    }
    public void  deleteIventory(Long id){
        inventoryRepository.deleteById(id);}
}
