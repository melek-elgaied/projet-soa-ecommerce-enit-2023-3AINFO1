package com.SOA.search.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.SOA.search.controller.api.SearchApi;
import com.SOA.search.dto.SearchQuery;
import com.SOA.search.model.Product;
import com.SOA.search.service.implementation.SearchServiceElasticSearchImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController implements SearchApi {

    private final SearchServiceElasticSearchImpl searchServiceElasticSearch;

    public SearchController(SearchServiceElasticSearchImpl searchServiceElasticSearch) {
        this.searchServiceElasticSearch = searchServiceElasticSearch;
    }

    @Override
    public List<Product> searchByText(SearchQuery searchquery) {
        return searchServiceElasticSearch.searchByText(searchquery);
    }

    @Override
    public Iterable<Product> findAll() {
        return searchServiceElasticSearch.findAll();
    }


}
