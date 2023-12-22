package com.enit.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.persistence.TypedQuery;

import com.enit.domain.Discount;
import com.enit.domain.ProductPrice;

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

    public List<Discount> findDiscountsByProduct(ProductPrice product) {
        TypedQuery<Discount> query = em.createQuery("SELECT d FROM Discount d WHERE d.product = :product", Discount.class);
        query.setParameter("product", product);
        return query.getResultList();
    }
//    public Optional<Discount> findMaxPercentageDiscountByProduct(ProductPrice product) {
//        TypedQuery<Discount> query = em.createQuery(
//                "SELECT d FROM Discount d " +
//                        "WHERE d.product = :product " +
//                        "AND d.discountValidation = true " +
//                        "ORDER BY d.discountPercentage DESC",
//                Discount.class);
//        query.setParameter("product", product);
//        query.setMaxResults(1);
//        List<Discount> resultList = query.getResultList();
//        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
//    }
    public Optional<Discount> findMaxPercentageDiscountByProduct(ProductPrice product) {
    TypedQuery<Discount> query = em.createQuery(
                    "SELECT d FROM Discount d " +
                            "WHERE d.product = :product " +
                            "AND d.discountValidation = true " +
                            "ORDER BY d.discountPercentage DESC",
                    Discount.class)
            .setParameter("product", product)
            .setMaxResults(1);

        return query.getResultList().stream().findFirst();
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
