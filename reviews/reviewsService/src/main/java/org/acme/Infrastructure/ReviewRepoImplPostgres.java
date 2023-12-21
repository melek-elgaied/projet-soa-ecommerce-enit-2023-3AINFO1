package org.acme.Infrastructure;

import java.util.List;

import org.acme.Domain.ProductID;
import org.acme.Domain.Review;
import org.acme.Domain.ReviewRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ReviewRepoImplPostgres implements ReviewRepo{

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Override
    public void addReview(Review review) {
        System.out.println("working fine");
        client.query(String.format("INSERT INTO reviews (review_id, user_id, product_id, avis) VALUES ('%s', '%s', '%s', '%s')",
        review.getReviewID(),
              review.getUserID().userID(),
              review.getProductID().productID(),
              review.getAvis().avis()))
        .execute().await().indefinitely();
    }

    @Override
    public List<Review> getProductReviews(ProductID productID) {
        return client.query(String.format("SELECT *, name FROM reviews Where product_id='%s",productID)).execute();
        }
    
    

}
