package com.enit.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import com.enit.domain.Discount;
import com.enit.domain.Product;

@ApplicationScoped
@Transactional
public class DiscountRepository {

    @Inject
    EntityManager em;

    public Optional<Discount> findDiscountById(UUID discountId) {
        return Optional.ofNullable(em.find(Discount.class, discountId));
    }

    public List<Discount> findAllDiscounts() {
        return em.createQuery("SELECT d FROM Discount d", Discount.class).getResultList();
    }

    public List<Discount> findDiscountsByProduct(Product product) {
        TypedQuery<Discount> query = em.createQuery("SELECT d FROM Discount d WHERE d.product = :product", Discount.class);
        query.setParameter("product", product);
        return query.getResultList();
    }

    public void createDiscount(Discount discount) {
        em.persist(discount);
    }

    public void updateDiscount(Discount discount) {
        em.merge(discount);
    }

    public void deleteDiscount(UUID discountId) {
        em.remove(em.find(Discount.class, discountId));
    }
}
