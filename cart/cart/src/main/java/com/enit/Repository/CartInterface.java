package com.enit.Repository;

import com.enit.Domain.Cart;


public interface CartInterface {

    public Cart openCart(String customerId);

    public void closeCart(String customerId);

    public void addItem(String customerId, Long productId, Integer quantity);

    public void removeItem(String customerId, Long productId, Integer quantity);

    //public void updateCart(String customerId, Long productId, Integer quantity);

    public Cart getCart(String customerId);
}