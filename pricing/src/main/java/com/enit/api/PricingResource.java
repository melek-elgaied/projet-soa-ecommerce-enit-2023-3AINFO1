package com.enit.api;


import com.enit.domain.ProductPrice;
import com.enit.domain.Discount;
import com.enit.service.PricingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/pricing")
public class PricingResource {

    @Inject
    PricingService pricingService;

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

//    @GET
//    @Path("/orderPrice/{productList}")
//    public double orderPrice(@QueryParam("productList") List<UUID> productList) {
//        return pricingService.calculateOrderPrice(productList);
//    }

    /**
     * Recevoir "order" (produits + quantités) et calculer le total
     * List<String>=List<"idProduct:quantity"> => Map<UUID, Integer>= Map<idProduct, quantity>
     */
    @GET
    @Path("/{orderTotalPrice}")
    public double orderPriceTotal(@QueryParam("productListOrder") List<String> productListOrder) {
        Map<UUID, Integer> productList = new HashMap<>();
        for (String entry : productListOrder) {
            String[] parts = entry.split(":");
            if (parts.length == 2) {
                UUID productId = UUID.fromString(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                productList.put(productId, quantity);
            } else if (parts.length == 1) {
                UUID productId = UUID.fromString(parts[0]);
                productList.put(productId, 1);
            }
        }
        return pricingService.calculateOrderPriceTotal(productList);
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


