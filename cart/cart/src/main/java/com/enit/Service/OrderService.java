package com.enit.Service;

import jakarta.ws.rs.*;



import com.enit.Domain.OrderRequestDTO;

@RegisterRestClient(baseUri = "http://localhost:8084/")
public interface OrderService {
    @POST
    @Path("/create")
    void createOrder(OrderRequestDTO request);
    
} 


