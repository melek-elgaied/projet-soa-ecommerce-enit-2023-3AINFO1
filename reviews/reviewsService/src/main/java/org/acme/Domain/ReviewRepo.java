package org.acme.Domain;

import java.util.List;

public interface ReviewRepo {

    public void addReview(Review review);
    public List<Review> getProductReviews(ProductID productID);
    
} 
