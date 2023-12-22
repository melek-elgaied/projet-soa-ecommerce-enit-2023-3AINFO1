package com.enit.api;


import com.enit.domain.ProductPrice;
import com.enit.domain.Discount;
import com.enit.service.PricingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/pricing")
public class PricingResource {

    @Inject
    private PricingService pricingService;

    @GET
    @Path("/{id}")
    public Response priceByProductId(@PathParam("id") UUID id) {
        Optional<ProductPrice> o= pricingService.getPriceByProductId(id);
        if(o.isPresent()){
            return Response.status(Response.Status.OK).entity(o.get()).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity(id).build();
        }
    }

    @GET
    @Path("/allProducts")
    public List<ProductPrice> allPrices() {
        return pricingService.getAllPrices();
    }

    @GET
    public double orderPrice(List<UUID> productList) {
        return pricingService.calculateOrderPrice(productList);
    }

    @POST
    @Path("/addPrice")
    public void addPrice(@QueryParam("id") UUID idProduct, @QueryParam("price") double price) {
        pricingService.addPrice(idProduct, price);
    }

    @PUT
    @Path("/updatePrice")
    public void updatePrice(@QueryParam("id") UUID idProduct, @QueryParam("price") double price) {
        pricingService.updatePrice(idProduct, price);
    }

    @POST
    @Path("/addDiscount")
    public void addDiscount(@QueryParam("idProduct") UUID idProduct,
                                @QueryParam("percentage") double percentage,
                                @QueryParam("discountStartDate") LocalDateTime discountStartDate,
                                @QueryParam("discountEndDate") LocalDateTime discountEndDate) {
        pricingService.addDiscount(idProduct, percentage, discountStartDate, discountEndDate);
    }

    @GET
    @Path("/showDiscounts")
    public List<Discount> showDiscount() {
        return pricingService.getAllDiscounts();
    }
    @PUT
    @Path("/extendDiscountEndDate")
    public void extendDiscountEndDate(@QueryParam("idProduct") UUID idProduct,
                                          @QueryParam("discountEndDate") LocalDateTime discountEndDate) {
        pricingService.extendDiscountEndDate(idProduct, discountEndDate);
    }

    @PUT
    @Path("/extendDiscountStartDate")
    public void extendDiscountStartDate(@QueryParam("idProduct") UUID idProduct,
                                            @QueryParam("discountStartDate") LocalDateTime discountStartDate) {
        pricingService.extendDiscountStartDate(idProduct, discountStartDate);
    }
}


