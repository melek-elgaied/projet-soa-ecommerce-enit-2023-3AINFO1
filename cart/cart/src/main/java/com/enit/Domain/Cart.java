package com.enit.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Cart { 
    // not sure
    //private UUID cartId;

    private String customerId;
    private Date modificationDate;
    //private Map<Long, Integer> items;

    private double totalPrice;
    private List<Product> products;

     public Cart() {
        //this.cartId = UUID.randomUUID();
        this.products = new ArrayList<>();
        this.modificationDate = new Date();
        
        this.totalPrice= 0;
    }

    public Cart(String customerId) {
        this();
        this.customerId = customerId;
        this.products = new ArrayList<>();
        this.modificationDate = new Date();

        this.totalPrice= 0;
    }
    public Cart(String customerId, Date modificationDatee, List<Product> products) {
        //this.cartId = cartId;
        this.customerId = customerId;
        this.modificationDate = modificationDatee;
        this.products = products;

        this.totalPrice= 0;
    } 
   
    /*public UUID getCartId() {
        return cartId;
    } */
     
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date ModificationDate) {
        this.modificationDate = ModificationDate;
    } 

    /*public void updateLastModifiedDate() {
        this.modificationDate = new Date();
    }*/

    public double getTotalPrice(){
        return this.totalPrice;
    } 

    public void setTotalPrice(double totalPrice){
        this.totalPrice= totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Long productId, int quantity) {

        int index=-1;
        Iterator<Product> iterator = products.iterator();
        while ((index==-1) && (iterator.hasNext())) {
            Product product = iterator.next();
            if(product.getIdProduct()== productId){
                index = products.indexOf(product);
                product.setQuantity(product.getQuantity() + quantity);
                this.products.set(index, product);
            }
        }
       
        if (index ==-1) {
           Product newProduct= new Product(productId, quantity);
           products.add(newProduct);
            
        } 
        this.modificationDate = new Date();
    }

    public void removeProduct(Long productId, int quantity){
        int index=-1;
        Iterator<Product> iterator = products.iterator();
        while ((index==-1) && (iterator.hasNext())) {
            Product product = iterator.next();
            if(product.getIdProduct().compareTo(productId)==0){
                index = products.indexOf(product);
                if(product.getQuantity()> quantity){
                    product.setQuantity(product.getQuantity() - quantity);
                    this.products.set(index, product);
                }else if(product.getQuantity() == quantity){
                    this.products.remove(index);
                }else{
                    System.err.println("Quantity is bigger than the existing");

                    return;
                }
            }
        }
        
        this.modificationDate = new Date();
    }

   
    
}