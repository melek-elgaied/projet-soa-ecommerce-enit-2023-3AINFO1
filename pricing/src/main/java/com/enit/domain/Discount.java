package com.enit.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.time.LocalDateTime;
import com.enit.domain.*;
@Entity
@Data
public class Discount{
    @Id
    UUID discountId;
    double percentage;
    LocalDateTime startDate;
    LocalDateTime endDate;
    boolean valid;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public Discount()
    {
        this.valid= false;
    }
    public Discount(UUID discountId, double percentage, LocalDateTime startDate, LocalDateTime endDate, Product product) {
        this.discountId = discountId;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
        LocalDateTime currentDate = LocalDateTime.now();
        if(currentDate.isEqual(startDate) || currentDate.isEqual(endDate) ||
                (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)))
        {
            this.valid = true;
        }
        else {
            this.valid= false;
        }
        this.product=product;

    }
}