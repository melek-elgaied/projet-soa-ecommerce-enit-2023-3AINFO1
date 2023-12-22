package com.SOA.search.controller;


import com.SOA.search.controller.api.IndexApi;
import com.SOA.search.model.Product;
import com.SOA.search.service.implementation.IndexElasticSearchImpl;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController implements IndexApi {

    private final IndexElasticSearchImpl indexElasticSearch;

    public IndexController(IndexElasticSearchImpl indexElasticSearch) {
        this.indexElasticSearch = indexElasticSearch;
    }

    @Override
    public void index(List<Product> products) {
        indexElasticSearch.index(products);
    }

    @Override
    public void reindex(Product products) {

    }

    @Override
    public void removeFromIndex(long id) {

    }
}
