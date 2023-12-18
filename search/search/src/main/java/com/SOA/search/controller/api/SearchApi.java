package com.SOA.search.controller.api;


import com.SOA.search.dto.SearchQuery;
import com.SOA.search.model.Product;
import com.SOA.search.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api("searching by plain text")
public interface SearchApi {

    @GetMapping(value = Constants.SEARCH_BY_TEXT_ENDPOINT)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public List<Product> searchByText(@RequestBody SearchQuery searchquery);
}
