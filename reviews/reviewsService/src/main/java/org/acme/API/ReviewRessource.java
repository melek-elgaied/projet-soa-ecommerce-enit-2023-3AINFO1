package org.acme.API;

import java.util.List;
import java.util.UUID;

import org.acme.API.DTOS.AddReviewDTO;
import org.acme.Domain.ProductID;
import org.acme.Domain.Review;
import org.acme.Service.ReviewService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/review")
public class ReviewRessource {

    @Inject
    ReviewService reviewService;

    @POST
    @Path("/add-review")
    public void addReview(AddReviewDTO addReviewDTO){

        this.reviewService.addReview(addReviewDTO.userID(), addReviewDTO.productID(), addReviewDTO.avis());
    }
    @POST
    @Path("/get-product-reviews")
    public List<Review>  getProductReviews(ProductID productID){
        return this.reviewService.getProductReviews(productID);
        
    }
}
