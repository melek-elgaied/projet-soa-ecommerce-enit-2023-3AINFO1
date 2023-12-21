package com.enit.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import com.enit.domain.Product;

@ApplicationScoped
@Transactional
public class ProductRepository {

    @Inject
    EntityManager em;

    public Optional<Product> findProductById(UUID productId) {
        return Optional.ofNullable(em.find(Product.class, productId));
    }

    public List<Product> findAllProducts() {
        return em.createQuery("from Product p ",Product.class).getResultList();
    }

    public void addProduct(Product product) {
        em.persist(product);
    }

    public void updateProduct(Product product) {
        em.merge(product);
    }

//    public void deleteProduct(UUID productId) {
//        em.remove(em.find(Product.class, productId));
//    }
}
