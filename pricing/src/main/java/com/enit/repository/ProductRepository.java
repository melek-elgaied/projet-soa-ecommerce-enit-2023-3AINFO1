package com.enit.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import com.enit.domain.ProductPrice;

@ApplicationScoped
@Transactional
public class ProductRepository {

    @Inject
    EntityManager em;

    public Optional<ProductPrice> findProductById(UUID productId) {
        return Optional.ofNullable(em.find(ProductPrice.class, productId));
    }

    public List<ProductPrice> findAllProducts() {
        return em.createQuery("from Product p ", ProductPrice.class).getResultList();
    }

    public void addProduct(ProductPrice product) {
        em.persist(product);
    }

    public void updateProduct(ProductPrice product) {
        em.merge(product);
    }

//    public void deleteProduct(UUID productId) {
//        em.remove(em.find(Product.class, productId));
//    }
}
