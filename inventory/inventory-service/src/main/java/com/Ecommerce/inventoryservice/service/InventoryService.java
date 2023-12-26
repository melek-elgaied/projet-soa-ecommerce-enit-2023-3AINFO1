package com.Ecommerce.inventoryservice.service;

import com.Ecommerce.inventoryservice.model.Inventory;
import com.Ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public boolean updateQuantityProduct(Long productId, int addedQuantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            int newQuantity = inventory.getStockQuantity() + addedQuantity;
            inventory.setStockQuantity(newQuantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }
    public Optional<Integer> getProductReservedQuantity(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            return Optional.of(inventory.get().getReservedQuantity());
        }
        return Optional.of(0);
    }
    public Optional<Integer> getProductAvailableQuantity(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            return Optional.of(inventory.get().getStockQuantity()-inventory.get().getReservedQuantity());
        }
        return Optional.of(0);
    }
    @Transactional
    public boolean enregistrerSortieCommande(Long productId, int quantityToDeduct) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            int reservedQuantity = inventory.getReservedQuantity();
            if ( reservedQuantity>= quantityToDeduct) {
                int newReservedQuantity = inventory.getReservedQuantity() - quantityToDeduct;
                int newStockQuantity = inventory.getStockQuantity() - quantityToDeduct;
                inventory.setReservedQuantity(newReservedQuantity);
                inventory.setStockQuantity(newStockQuantity);

                inventoryRepository.save(inventory);
                return true;
            }

        }
        return false;
    }
    public boolean reserveProduct(Long productId, int reservedQuantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(productId);

        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            int stockQuantity = inventory.getStockQuantity();
            if (stockQuantity >= reservedQuantity) {
                int newReservedQuantity = inventory.getReservedQuantity() + reservedQuantity;
                inventory.setReservedQuantity(newReservedQuantity);
                inventoryRepository.save(inventory);
                return true;
            } else {
                return false;
            }
        }
        return false;

    }
    public boolean cancelProductRservation(Long productId, int reservedQuantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(productId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            int oldreservedQuantity = inventory.getReservedQuantity();
            if(oldreservedQuantity >= reservedQuantity )
            {int newReservedQuantity = oldreservedQuantity - reservedQuantity;
                inventory.setReservedQuantity(newReservedQuantity);
                inventoryRepository.save(inventory);
                return true;
            }}

        return false ;
    }
}
