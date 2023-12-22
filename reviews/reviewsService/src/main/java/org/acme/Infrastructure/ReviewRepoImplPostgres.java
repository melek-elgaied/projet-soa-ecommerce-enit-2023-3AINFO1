package org.acme.Infrastructure;

import java.util.ArrayList;
import java.util.List;


import org.acme.Domain.ProductID;
import org.acme.Domain.Review;
import org.acme.Domain.ReviewRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;




@ApplicationScoped
public class ReviewRepoImplPostgres implements ReviewRepo{

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Override
    public void addReview(Review review) {
        client.query(String.format("INSERT INTO reviews (review_id, user_id, product_id, avis) VALUES ('%s', '%s', '%s', '%s')",
        review.getReviewID(),
              review.getUserID().userID(),
              review.getProductID().productID(),
              review.getAvis().avis()))
        .execute().await().indefinitely();
    }

    @Override
    public Multi<Review> getProductReviews(ProductID productID) {
        Uni<RowSet<Row>> rowSet = client.query(String.format("SELECT * FROM reviews WHERE product_id='%s'", productID)).execute();
        Multi<Review> reviews = rowSet
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Review::from);
        return reviews;
    }

}
