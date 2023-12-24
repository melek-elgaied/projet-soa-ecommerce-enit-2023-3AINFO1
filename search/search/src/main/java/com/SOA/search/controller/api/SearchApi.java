package com.SOA.search.controller.api;


import com.SOA.search.dto.SearchQuery;
import com.SOA.search.dto.SearchQueryWithPrice;
import com.SOA.search.model.Product;
import com.SOA.search.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Api("searching by plain text")
public interface SearchApi {

    @GetMapping(value = Constants.SEARCH_BY_TEXT_ENDPOINT)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public List<Product> searchByText(@RequestBody SearchQuery searchquery);


    @GetMapping(value = Constants.SEARCH_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public Optional<Product> searchById(@PathVariable(value = "id") long id);

    @GetMapping(value = Constants.SEARCH_BY_NAME)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public List<Product> searchByName(@PathVariable(value = "name") String name) throws IOException;


    @GetMapping(value = Constants.FUZZY_SEARCH)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public List<Product> fuzzySearch(@RequestBody SearchQuery searchquery) throws IOException;

    @GetMapping(value = Constants.PRICE_SEARCH)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public List<Product> priceSearch(@RequestBody SearchQueryWithPrice searchQueryWithPrice) throws IOException;



    @GetMapping(value = Constants.FIND_ALL)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "products are fetched "),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public Iterable<Product> findAll();



}
