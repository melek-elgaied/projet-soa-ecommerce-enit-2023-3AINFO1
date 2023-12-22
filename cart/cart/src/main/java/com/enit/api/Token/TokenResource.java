package com.enit.api.Token;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import com.enit.Service.TokenManager;


@Path("/api")
public class TokenResource {

    @Inject
    TokenManager tokenManager;

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TokenResponse getToken(TokenRequest req) {
        System.out.println("-----------token requested");
        TokenResponse res = tokenManager.getAccessToken(req.getEmail(),req.getPassword());
        System.out.println(res);
        return res;
    }
}