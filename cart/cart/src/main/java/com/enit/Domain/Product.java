package com.enit.Domain;

public class Product {
    private Long idProduct;
    private int quantity;
    private double price;

    //Constructor
    public Product(){

    }

    public Product(Long id){
        this.idProduct= id;
    }

     public Product(Long id, int quantity){
        this.idProduct= id;
        this.quantity= quantity;
    }

    public Product(Long id, double price){
        this.idProduct= id;
        this.price= price;

        // not sure about this one
        this.quantity= 1;
    }

    public Product(Long id, double price, int quantity){
        this.idProduct= id;
        this.price= price;
        this.quantity= quantity;
    }

    // getters and setters;
    public Long getIdProduct(){
        return this.idProduct;
    }

    public void setIdProduct(Long id){
        this.idProduct=id;
    }
    
    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity= quantity;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price= price;
    }
    
}