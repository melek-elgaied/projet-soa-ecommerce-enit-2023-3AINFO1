package com.SOA.search.controller.api;

import com.SOA.search.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Api("search by similarity")
public interface SimilarityApi {

    @GetMapping(value = "api/v1/similar/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "fetched similar product "),
            @ApiResponse(code = 400, message = "Bad Request ")
    })
    public List<Product> getSimilarProducts(@PathVariable long id);
}
