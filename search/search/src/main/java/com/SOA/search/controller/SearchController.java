package com.SOA.search.controller;

import com.SOA.search.controller.api.SearchApi;
import com.SOA.search.dto.SearchQuery;
import com.SOA.search.model.Product;
import com.SOA.search.service.implementation.SearchServiceElasticSearchImpl;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public Optional<Product> searchById(long id) {
        return searchServiceElasticSearch.searchById(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return searchServiceElasticSearch.findAll();
    }


}
