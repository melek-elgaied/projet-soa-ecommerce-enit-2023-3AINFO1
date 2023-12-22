package com.enit.Domain;

import java.util.List;

public class OrderRequestDTO {

    private String userId;
    private Double cost;
    private Boolean isReserved;
    private Boolean isPaid;

    private List<Product> products;
    private List<Delivery> delivery;

    public static class Delivery {
        private String address;
        private String zip;

        public Delivery() {}

        // Getters and Setters for Delivery

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }
    }

    // Constructors

    public OrderRequestDTO() {}

    public OrderRequestDTO(String userId, Double cost, List<Product> products) {
        this.userId = userId;
        this.cost = cost;
        this.isReserved = false;
        this.isPaid = false;
        this.products = products;
    }

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean isReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Delivery> delivery) {
        this.delivery = delivery;
    }
}
