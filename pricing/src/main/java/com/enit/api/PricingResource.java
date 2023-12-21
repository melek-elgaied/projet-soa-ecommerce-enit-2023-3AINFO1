package com.enit.api;


import com.enit.domain.ProductPrice;
import com.enit.service.PricingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response priceById(@PathParam("id") UUID id) {
        Optional<ProductPrice> optionalProductPrice = Optional.ofNullable(pricingService.getPriceById(id));
        return optionalProductPrice.map(productPrice ->
                        Response.status(Response.Status.OK).entity(productPrice).build())
                .orElseGet(() ->
                        Response.status(Response.Status.NOT_FOUND).entity(id).build());
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allPrices(List<UUID> productList) {
        List<ProductPrice> productPrices = pricingService.getAllPrices(productList);
        return Response.status(Response.Status.OK).entity(productPrices).build();
    }

    @GET
    @Path("/commande")
    public Response commandePrice(List<UUID> productList) {
        double totalPrice = pricingService.calculateCommandePrice(productList);
        return Response.status(Response.Status.OK).entity(totalPrice).build();
    }

    @POST
    @Path("/addPrice")
    public Response addPrice(@QueryParam("id") UUID idPrice, @QueryParam("price") double price) {
        Response response = pricingService.addPrice(idPrice, price);
        return Response.status(response.getStatus()).entity(response.getEntity()).build();
    }

    @PUT
    @Path("/updatePrice")
    public Response updatePrice(@QueryParam("id") UUID idPrice, @QueryParam("price") double price) {
        Response response = pricingService.updatePrice(idPrice, price);
        return Response.status(response.getStatus()).entity(response.getEntity()).build();
    }

    @POST
    @Path("/addDiscount")
    public Response addDiscount(@QueryParam("idProduct") UUID idProduct,
                                @QueryParam("percentage") double percentage,
                                @QueryParam("discountStartDate") LocalDateTime discountStartDate,
                                @QueryParam("discountEndDate") LocalDateTime discountEndDate) {
        Response response = pricingService.addDiscount(idProduct, percentage, discountStartDate, discountEndDate);
        return Response.status(response.getStatus()).entity(response.getEntity()).build();
    }

    @PUT
    @Path("/extendDiscountEndDate")
    public Response extendDiscountEndDate(@QueryParam("idProduct") UUID idProduct,
                                          @QueryParam("discountEndDate") LocalDateTime discountEndDate) {
        Response response = pricingService.extendDiscountEndDate(idProduct, discountEndDate);
        return Response.status(response.getStatus()).entity(response.getEntity()).build();
    }

    @PUT
    @Path("/extendDiscountStartDate")
    public Response extendDiscountStartDate(@QueryParam("idProduct") UUID idProduct,
                                            @QueryParam("discountStartDate") LocalDateTime discountStartDate) {
        Response response = pricingService.extendDiscountStartDate(idProduct, discountStartDate);
        return Response.status(response.getStatus()).entity(response.getEntity()).build();
    }
}


