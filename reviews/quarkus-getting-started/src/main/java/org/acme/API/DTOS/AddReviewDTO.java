package org.acme.API.DTOS;

import org.acme.Domain.Avis;
import org.acme.Domain.ProductID;
import org.acme.Domain.UserID;

public record AddReviewDTO(UserID userID, ProductID productID, Avis avis) {
} 