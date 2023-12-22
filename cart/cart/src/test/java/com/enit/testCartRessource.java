package com.enit;

import static io.restassured.RestAssured.given;

import java.util.UUID;

import org.junit.Test;

import com.enit.Domain.Cart;
import com.enit.Service.CartService;

import io.restassured.http.ContentType;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@QuarkusTest
public class testCartRessource { 
    @InjectMock
    CartService cartService;

    @Test
    public void testOpenCart() {
        Cart newCart = new Cart("customer123");

        when(cartService.openCart("customer123")).thenReturn(newCart);

        given()
                .when().post("/cart/open/customer123")
                .then()
                .statusCode(jakarta.ws.rs.core.Response.Status.OK.getStatusCode())
                .contentType(ContentType.JSON)
                .body("customerId", is("customer123"));

        verify(cartService, times(1)).openCart("customer123");
        reset(cartService);
    }

    @Test
    public void testCloseCart() {
        UUID cartId = UUID.randomUUID();

        given()
                .when().post("/cart/close/" + cartId)
                .then()
                .statusCode(jakarta.ws.rs.core.Response.Status.NO_CONTENT.getStatusCode());

        verify(cartService, times(1)).closeCart(cartId);
        reset(cartService);
    }

   
    
}
