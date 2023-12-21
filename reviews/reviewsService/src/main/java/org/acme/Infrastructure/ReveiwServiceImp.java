package org.acme.Infrastructure;

import java.util.List;

import org.acme.Domain.Avis;
import org.acme.Domain.ProductID;
import org.acme.Domain.Review;
import org.acme.Domain.ReviewRepo;
import org.acme.Domain.UserID;
import org.acme.Service.ReviewService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReveiwServiceImp implements ReviewService{
@Inject
ReviewRepo reviewRepo;

    @Override
    public void addReview(UserID userID, ProductID productID, Avis avis) {
        Review reviewToAdd = Review.of(userID, productID, avis);
        this.reviewRepo.addReview(reviewToAdd);
        
    }

    @Override
    public List<Review> getProductReviews(ProductID productID) {
        return this.reviewRepo.getProductReviews(productID);
    }
    
}
