# Inventory Microservice 

This microservice provides endpoints for managing product inventory.

## Endpoints
### 1. Retrieve Product information based on its name
**Endpoint:**
```shell script
GET /api/inventory/{productName}
```
### 2. Get All Inventory

returns a list with all products in stock./n
**Endpoint:**
```shell script
GET /api/inventory/all
```
### 3. Add Inventory

Adds a new product to the inventory.
**Endpoint:** 
```shell script
POST /api/inventory/add
```
### 4.  Update Inventory

updates an already existing product inventory (can modify many attributes at one).
**Endpoint:**
```shell script
PUT /api/inventory/update
```
### 5.  Update Quantity Product

Updates the quantity of an existing product in the inventory.
**Endpoint:** 
```shell script
PUT /api/inventory/update/{productId}/{addedQuantity}
```
### 6.  Delete Inventory

Deletes an inventory product by ID.
**Endpoint:**
```shell script
DELETE /api/inventory/delete/{id}
```
### 7. Get Product available  Quantity In Stock

Gets the available quantity of a specific product.
**Endpoint:** 
```shell script
GET /api/inventory/quantity/{productId}
```
### 8. Get Product Reserved Quantity

Gets the reserved quantity of a specific product.
**Endpoint:**
```shell script 
GET /api/inventory/reservedQuantity/{productId}
```
### 9.  Enregistrer Sortie Commande

Records the outgoing quantity of a product for an order.
**Endpoint:** 
```shell script
POST /api/inventory/sortie/{productId}/{quantity}
```
### 10. Reserve a Product

Reserves a quantity of a product.
**Endpoint:**
```shell script
POST /api/inventory/reserve/{productId}/{reservedQuantity}
```
### 11.  Cancel a Product Reservation

Cancels a previously reserved quantity of a product.
**Endpoint:** 
```shell script
POST /api/inventory/cancel/{productId}/{reservedQuantity}
```
