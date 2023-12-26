package com.Ecommerce.inventoryservice;

import com.Ecommerce.inventoryservice.model.Inventory;
import com.Ecommerce.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner LoadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setProductName("iphone_13");
			inventory.setStockQuantity(100);

			Inventory inventory1 = new Inventory();
			inventory1.setProductName("iphone_13_red");
			inventory1.setStockQuantity(50); // Assuming 50 as available quantity for 'iphone_13_red'

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
