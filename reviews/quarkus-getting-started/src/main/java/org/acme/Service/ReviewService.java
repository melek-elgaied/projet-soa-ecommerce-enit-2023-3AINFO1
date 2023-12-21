package org.acme.Service;

import java.util.List;

import org.acme.Domain.Avis;
import org.acme.Domain.ProductID;
import org.acme.Domain.Review;
import org.acme.Domain.UserID;

public interface ReviewService {
    public void addReview(UserID userID, ProductID productID, Avis avis);
    public List<Review> getProductReviews(ProductID productID);

}
