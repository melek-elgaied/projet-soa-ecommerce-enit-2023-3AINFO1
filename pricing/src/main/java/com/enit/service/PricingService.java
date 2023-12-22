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

    @Transactional
    public List<ProductPrice> getAllPrices() {
        return productRepository.findAllProducts();
    }

    @Transactional
    public double calculateOrderPrice(List<UUID> productList) {
        double somme = 0;
        for (UUID id : productList) {
            Optional<ProductPrice> productPriceOptional = getPriceByProductId(id);
            if (productPriceOptional.isPresent()) {
                ProductPrice productPrice = productPriceOptional.get();
                double price = productPrice.getProductPrice();
                somme += price;
            }
        }
        return somme;
    }

    @Transactional
    public void addPrice(UUID idProduct, double price) {
        ProductPrice productPrice=new ProductPrice(idProduct,price);
        productRepository.addProduct(productPrice);
    }

    @Transactional
    public void updatePrice(UUID idProduct, double price) {
        ProductPrice productPrice=new ProductPrice(idProduct,price);
        productRepository.updateProduct(productPrice);
    }

    @Transactional
    public void addDiscount(UUID idProduct, double percentage, LocalDateTime discountStartDate, LocalDateTime discountEndDate) {
        Discount discount=new Discount(idProduct,percentage,discountStartDate,discountEndDate);
        discountRepository.createDiscount(discount);
    }

    @Transactional
    public void extendDiscountEndDate(UUID idProduct, LocalDateTime discountEndDate) {
        Optional<Discount> optionalDiscount = discountRepository.findDiscountById(idProduct);
        optionalDiscount.ifPresent(discount -> {
            discount.setDiscountEndDate(discountEndDate);
            discountRepository.updateDiscount(discount);
        });
    }

    @Transactional
    public void extendDiscountStartDate(UUID idProduct, LocalDateTime discountStartDate) {
        Optional<Discount> optionalDiscount = discountRepository.findDiscountById(idProduct);
        optionalDiscount.ifPresent(discount -> {
            discount.setDiscountStartDate(discountStartDate);
            discountRepository.updateDiscount(discount);
        });
    }
}
