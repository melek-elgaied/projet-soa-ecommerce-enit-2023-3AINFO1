package com.enit.service;

import com.enit.domain.Discount;
import com.enit.domain.ProductPrice;
import com.enit.repository.DiscountRepository;
import com.enit.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PricingService {

    @Inject
    DiscountRepository discountRepository;

    @Inject
    ProductRepository productRepository;

    @Transactional
    public Optional<ProductPrice> getPriceByProductId(UUID id) {
        return productRepository.findProductById(id);
    }

    public List<ProductPrice> getAllPrices() {
        return productRepository.findAllProducts();
    }

    public double calculateOrderPrice(List<UUID> productList) {
        return 0;
    }

    public void addPrice(UUID idProduct, double price) {
        ProductPrice productPrice=new ProductPrice(idProduct,price);
        productRepository.addProduct(productPrice);
    }

    public void updatePrice(UUID idProduct, double price) {
        ProductPrice productPrice=new ProductPrice(idProduct,price);
        productRepository.updateProduct(productPrice);
    }

    public void addDiscount(UUID idProduct, double percentage, LocalDateTime discountStartDate, LocalDateTime discountEndDate) {
        Discount discount=new Discount(idProduct,percentage,discountStartDate,discountEndDate);
        discountRepository.createDiscount(discount);
    }

    public void extendDiscountEndDate(UUID idProduct, LocalDateTime discountEndDate) {
    }

    public void extendDiscountStartDate(UUID idProduct, LocalDateTime discountStartDate) {
    }
}
