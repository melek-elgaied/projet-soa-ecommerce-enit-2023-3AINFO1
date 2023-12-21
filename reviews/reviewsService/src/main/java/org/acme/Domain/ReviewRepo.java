package org.acme.Domain;

import java.util.List;

public interface ReviewRepo {

    public void addReview(Review review);
    public Multi<Review> getProductReviews(ProductID productID);
    
} 
