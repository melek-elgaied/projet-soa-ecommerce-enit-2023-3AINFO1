package com.enit.Service;
import java.util.UUID;

import com.enit.Domain.Cart;
import com.enit.Repository.CartRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class CartService {
    @Inject
    CartRepository cartRepository;
   

    public Cart openCart(String customerId) {
        return cartRepository.openCart(customerId);
    }

    public void closeCart(UUID cartId) {
        cartRepository.closeCart(cartId.toString());
    }

  

    public Cart getCart(String customerId) {
        return cartRepository.getCart(customerId);
    }

    public void removeItem(String customerId, Long productId, Integer quantity) {
        cartRepository.removeItem(customerId, productId, quantity);
    }

    

    public void addItem(String customerId, Long productId, Integer quantity) {
        cartRepository.addItem(customerId, productId, quantity);
    }
   
}
