package com.SOA.search.controller.api;


import com.SOA.search.dto.SearchQuery;
import com.SOA.search.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api("searching by plain text")
public interface SearchApi {

    @GetMapping(value = "api/v1/search/")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request ")
    })
    public List<Product> search(@RequestBody SearchQuery searchquery);
}
