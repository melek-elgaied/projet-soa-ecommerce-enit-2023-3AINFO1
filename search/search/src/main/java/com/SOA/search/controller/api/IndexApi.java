package com.SOA.search.controller.api;


import com.SOA.search.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Api("indexing")
public interface IndexApi {

    @PostMapping(value = "api/v1/index")
    @ApiResponse(code = 200,message = "products are indexed successfully")
    public void index(List<Product> products);
}
