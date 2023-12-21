package org.acme.Domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.vertx.core.cli.annotations.Description;

public class Review {
    UUID reviewID;
    UserID userID;
    ProductID productID;
    Avis avis;

    @JsonCreator
    public static Review of(UserID userID,ProductID productId, Avis avis){
        return new Review(userID,productId,avis);
    }

    private Review(UserID userID, ProductID productID, Avis avis) {
        this.reviewID = UUID.randomUUID();
        this.userID = userID;
        this.productID = productID;
        this.avis = avis;
    }

    public UUID getReviewID() {
        return reviewID;
    }

    public UserID getUserID() {
        return userID;
    }

    public ProductID getProductID() {
        return productID;
    }

    public Avis getAvis() {
        return avis;
    }
    public static Review from(Row row) {
        return new Review(
            new reviewID(row.getString("reviewID")),
            new UserID(row.getString("userID")),
            new ProductID(row.getString("productID")),
            new Avis(row.getString("avis"))
        );
    }
    
}
