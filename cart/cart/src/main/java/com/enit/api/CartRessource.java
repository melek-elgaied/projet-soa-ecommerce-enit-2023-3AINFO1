package com.enit.api;

import com.enit.Domain.Cart;
import com.enit.Domain.Product;
import com.enit.Service.CartService;
import com.enit.Service.OrderService;
import com.enit.Domain.OrderRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartRessource {

    @Inject
    CartService cartService;
    @Inject
    OrderService orderService;
    private static final String PRICING_SERVICE_URL = "http://pricing : 8086"; 
   

    Client pricingClient = ClientBuilder.newClient();
    @POST
    @Path("/open/{customerId}")
    public Cart openCart(@PathParam("customerId") String customerId) {
        return cartService.openCart(customerId);
    }

    @POST
    @Path("/close/{cartId}")
    public void closeCart(@PathParam("cartId") UUID cartId) {
        cartService.closeCart(cartId);
    }
   


    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart getCart(@PathParam("customerId") String customerId) {
        return cartService.getCart(customerId);
    } 
    @POST
    @Path("/add-item/{customerId}/{productId}/{quantity}")
    public Response addItem(
            @PathParam("customerId") String customerId,
            @PathParam("productId") Long productId,
            @PathParam("quantity") Integer quantity) {

        // Fetch the product price from the Pricing service
        Double productPrice = fetchProductPrice(productId);

        // Set the price in Product object
        Product newProduct = new Product();
        newProduct.setIdProduct(productId);
        newProduct.setQuantity(quantity);
        newProduct.setPrice(productPrice * quantity);

        
        cartService.addItem(customerId, newProduct.getIdProduct(),newProduct.getQuantity());

       

        return Response.ok().build();
    }

    @POST
    @Path("/remove-item/{customerId}/{productId}/{quantity}")
    public Response removeItem(
            @PathParam("customerId") String customerId,
            @PathParam("productId") Long productId,
            @PathParam("quantity") Integer quantity) {

        // Fetch the product price from the Pricing service
        Double productPrice = fetchProductPrice(productId);

        // Set the price in  Product object
        Product removedProduct = new Product();
        removedProduct.setIdProduct(productId);
        removedProduct.setQuantity(quantity);
        removedProduct.setPrice(productPrice * quantity);

   
        cartService.removeItem(customerId, removedProduct.getIdProduct(),removedProduct.getQuantity());

        

        return Response.ok().build();
    }

    @POST
    @Path("/confirm/{cartId}")
    public void confirmCart(@PathParam("cartId") UUID cartId) {
        Cart cart = cartService.getCart(cartId.toString());
        if (cart != null) {
            
            if (cart.getTotalPrice() > 0) {
                throw new BadRequestException("Cart is already confirmed.");
            }

            // Fetch total price from Pricing service
            double totalPrice = fetchTotalPriceFromPricingService(cart.getProducts());

            // Set the total price 
            cart.setTotalPrice(totalPrice);
            OrderRequestDTO orderRequest = new OrderRequestDTO();
            orderRequest.setUserId(cart.getCustomerId());
            orderRequest.setCost(cart.getTotalPrice());
            orderRequest.setProducts(cart.getProducts());

          
            OrderRequestDTO.Delivery delivery = new OrderRequestDTO.Delivery();
            delivery.setAddress("");
            delivery.setZip("");
            orderRequest.setDelivery(List.of(delivery));

            // Call the Order service to create a new order
            orderService.createOrder(orderRequest);
            

          
        } else {
            
            throw  new NotFoundException("Cart not found with ID: " + cartId);
        }
    }


    private double fetchTotalPriceFromPricingService(List<Product> products) {
   

    // Prepare a list of product IDs
    List<Long> productIds = products.stream()
            .map(Product::getIdProduct)
            .collect(Collectors.toList());

    
    Response response = pricingClient
            .target(PRICING_SERVICE_URL + "/all")
            .request()
            .post(Entity.json(productIds), Response.class);

    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
        
        List<Double> productPrices = response.readEntity(new GenericType<List<Double>>() {});

        
        double totalPrice = products.stream()
                .mapToDouble(product -> {
                    int index = productIds.indexOf(product.getIdProduct());
                    if (index != -1) {
                        double price = productPrices.get(index);
                        return price * product.getQuantity();
                    } else {
                        return 0.0;
                    }
                })
                .sum();

        return totalPrice;
    } else {
        throw new InternalServerErrorException("Failed to fetch total price from Pricing service");
    }
}

private Double fetchProductPrice(Long productId) {
    
    Response response = pricingClient
            .target(PRICING_SERVICE_URL +  "/"+ productId)
            .request()
            .get();

    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
        return response.readEntity(Double.class);
    } else {
        throw new InternalServerErrorException("Failed to fetch product price from Pricing service");
    }
}

    
}
