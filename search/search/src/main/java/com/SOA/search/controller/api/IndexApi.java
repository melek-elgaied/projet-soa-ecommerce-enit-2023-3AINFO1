package com.SOA.search.controller.api;


import com.SOA.search.model.Product;
import com.SOA.search.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api("indexing")
public interface IndexApi {

    @PostMapping(value = Constants.INDEX_ENDPOINT)
    @ApiResponse(code = 201,message = "products are indexed successfully")
    public void index(@RequestBody List<Product> products);

    @PostMapping(value = Constants.REINDEX_ENDPOINT)
    @ApiResponse(code = 201,message = "product is reindex-ed successfully")
    public void reindex(@RequestBody Product products);

    @PostMapping(value = Constants.REMOVE_FROM_INDEX_ENDPOINT+"{id}")
    @ApiResponse(code = 201,message = "product is removed successfully")
    public void removeFromIndex(@PathVariable(name = "id") long id);


}
