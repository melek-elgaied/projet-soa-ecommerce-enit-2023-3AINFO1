package com.enit.service;

import com.enit.domain.Discount;
import com.enit.domain.ProductPrice;
import com.enit.domain.exceptions.EntityNotFoundException;
import com.enit.repository.DiscountRepository;
import com.enit.repository.ProductRepository;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
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
        Optional<ProductPrice> o = productRepository.findProductById(id);
        if (o.isPresent()) {
            ProductPrice productPrice = o.get();
            Optional<Discount> optionalDiscount = discountRepository.findMaxPercentageDiscountByProduct(productPrice);
            if (optionalDiscount.isPresent()) {
                Discount discount = optionalDiscount.get();
                double discountedPrice = productPrice.getProductPrice() * (1 - (discount.getDiscountPercentage() / 100));
                return Optional.of(new ProductPrice(productPrice.getProductId(), discountedPrice));
            } else {
                return o;
            }
        } else {
            throw new EntityNotFoundException("No product with id " + id.toString());
        }
    }


    @Transactional
    public List<ProductPrice> getAllPrices() {
        List<ProductPrice> allPrices = productRepository.findAllProducts();
        List<ProductPrice> discountedPrices = new ArrayList<>();
        for (ProductPrice p : allPrices) {
            Optional<ProductPrice> discountedPrice = getPriceByProductId(p.getProductId());
            discountedPrice.ifPresent(discountedPrices::add);
        }
        return discountedPrices;
    }

    @Transactional
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAllDiscounts();
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
    public double calculateOrderPriceTotal(Map<UUID, Integer> productList) {
        double total = 0;
        for (Map.Entry<UUID, Integer> entry : productList.entrySet()) {
            UUID productId = entry.getKey();
            int quantity = entry.getValue();

            Optional<ProductPrice> productPriceOptional = getPriceByProductId(productId);
            if (productPriceOptional.isPresent()) {
                ProductPrice productPrice = productPriceOptional.get();
                double price = productPrice.getProductPrice();
                total += (price * quantity);
            }
        }
        return total;
    }

    @Transactional
    public void addPrice(UUID idProduct, double price) {
        ProductPrice productPrice=new ProductPrice(idProduct,price);
        productRepository.addProduct(productPrice);
    }

    @Transactional
    public void updatePrice(UUID idProduct, double price) {
        Optional<ProductPrice> productOptional = getPriceByProductId(idProduct);
        if (productOptional.isPresent()) {
            ProductPrice product = productOptional.get();
            ProductPrice productPrice=new ProductPrice(idProduct,price);
            productRepository.updateProduct(productPrice);
        }else{
            throw new EntityNotFoundException("No product with id " + idProduct.toString());
        }
    }

    @Transactional
    public void addDiscount(UUID idProduct, double percentage, LocalDateTime discountStartDate, LocalDateTime discountEndDate) {
        Optional<ProductPrice> productOptional = getPriceByProductId(idProduct);
        if (productOptional.isPresent()) {
            ProductPrice product = productOptional.get();
            Discount discount=new Discount(product,percentage,discountStartDate,discountEndDate);
            discountRepository.createDiscount(discount);
        }
    }

    @Transactional
    public void extendDiscountEndDate(UUID idProduct, LocalDateTime discountEndDate) {
        Optional<ProductPrice> productOptional = productRepository.findProductById(idProduct);
        LocalDateTime currentDate = LocalDateTime.now();
        if (productOptional.isPresent()) {
            ProductPrice product = productOptional.get();
            List<Discount> discounts = discountRepository.findDiscountsByProduct(product);
            Optional<Discount> optionalDiscount = discounts.stream().findFirst();
            optionalDiscount.ifPresent(discount -> {
                discount.setDiscountEndDate(discountEndDate);
                if(discount.getDiscountStartDate().isBefore(currentDate.plusDays(1)) && discount.getDiscountEndDate().isAfter(currentDate.minusDays(1))) {
                    discount.setDiscountValidation(true);
                }else {
                    discount.setDiscountValidation(false);
                }
                discountRepository.updateDiscount(discount);
            });
        }else{
            throw new EntityNotFoundException("No discount with product id " + idProduct.toString());
        }
    }

    @Transactional
    public void extendDiscountStartDate(UUID idProduct, LocalDateTime discountStartDate) {
        Optional<ProductPrice> productOptional = productRepository.findProductById(idProduct);
        LocalDateTime currentDate = LocalDateTime.now();
        if (productOptional.isPresent()) {
            ProductPrice product = productOptional.get();
            List<Discount> discounts = discountRepository.findDiscountsByProduct(product);
            Optional<Discount> optionalDiscount = discounts.stream().findFirst();
            optionalDiscount.ifPresent(discount -> {
                discount.setDiscountStartDate(discountStartDate);
                if(discount.getDiscountStartDate().isBefore(currentDate.plusDays(1)) && discount.getDiscountEndDate().isAfter(currentDate.minusDays(1))) {
                    discount.setDiscountValidation(true);
                }else {
                    discount.setDiscountValidation(false);
                }
                discountRepository.updateDiscount(discount);
            });
        }else{
            throw new EntityNotFoundException("No discount with product id " + idProduct.toString());
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateDiscountValidationStatus() {
        List<Discount> allDiscounts = discountRepository.findAllDiscounts();
        LocalDateTime currentDate = LocalDateTime.now();
        for (Discount discount : allDiscounts) {
            if (!discount.isValid() && currentDate.isEqual(discount.getDiscountStartDate())) {
                discount.setDiscountValidation(true);
                discountRepository.updateDiscount(discount);
            } else if (discount.isValid() && currentDate.isEqual(discount.getDiscountEndDate().plusDays(1))) {
                discount.setDiscountValidation(false);
                discountRepository.updateDiscount(discount);
            }
        }
    }
}
