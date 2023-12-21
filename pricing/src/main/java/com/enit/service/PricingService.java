package com.enit.service;

import com.enit.domain.ProductPrice;
import com.enit.repository.DiscountRepository;
import com.enit.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PricingService {

    @Inject
    DiscountRepository discountRepository;

    @Inject
    ProductRepository productRepository;

    public ProductPrice getPriceById(UUID id) {
        return null;
    }

    public List<ProductPrice> getAllPrices(List<UUID> productList) {
        return null;
    }

    public double calculateCommandePrice(List<UUID> productList) {
        return 0;
    }

    public Response addPrice(UUID idPrice, double price) {
        return null;
    }

    public Response updatePrice(UUID idPrice, double price) {
        return null;
    }

    public Response addDiscount(UUID idProduct, double percentage, LocalDateTime discountStartDate, LocalDateTime discountEndDate) {
        return null;
    }

    public Response extendDiscountEndDate(UUID idProduct, LocalDateTime discountEndDate) {
        return null;
    }

    public Response extendDiscountStartDate(UUID idProduct, LocalDateTime discountStartDate) {
        return null;
    }
}
