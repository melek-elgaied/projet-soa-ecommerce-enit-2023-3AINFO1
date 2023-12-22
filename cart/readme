# Cart Microservice

The Cart microservice is responsible for managing the shopping cart functionality in our e-commerce platform. It allows users to add and remove items from their shopping carts, as well as view the contents of their carts.

## Service Endpoints

### GET CART:
Retrieve the contents of the shopping cart for a specific customer.
#### Endpoint:

```shell script
GET /carts/{customerId}
```
### Add Item to Cart:
Add an item to the shopping cart.
#### Endpoint:

```shell script
POST /carts/add-item/{customerId}/{productId}/{quantity}
```

### Remove an Item from Cart:
Remove an item from the shopping cart.
#### Endpoint:

```shell script
POST /carts/remove-item/{customerId}/{productId}/{quantity}
```

### Create Cart:
Create a shopping cart.
#### Endpoint:

```shell script
POST /carts/open/{customerId}
```
### Delete a Cart:
Delete a shopping cart.
#### Endpoint:

```shell script
POST /carts/close/{cartId}
```

### Confirm Cart:
 Confirm the contents of the shopping cart for a specific customer and Creation of a new Order.
#### Endpoint:
```shell script
POST /carts/confirm/{cartId}
```


## Technologies

This project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

Besides, Redis was used as in memory databases to save the shopping cart.

This project was tested using Swagger.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

