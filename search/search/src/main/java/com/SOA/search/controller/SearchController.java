package com.SOA.search.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.SOA.search.controller.api.SearchApi;
import com.SOA.search.dto.SearchQuery;
import com.SOA.search.dto.SearchQueryWithPrice;
import com.SOA.search.model.Product;
import com.SOA.search.service.implementation.SearchServiceElasticSearchImpl;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<Product> searchByName(String name) throws IOException {
        SearchResponse<Product> searchResponse =  searchServiceElasticSearch.matchProductsWithName(name);
        System.out.println(searchResponse.hits().hits().toString());
        List<Hit<Product>> listOfHits= searchResponse.hits().hits();
        List<Product> listOfProducts  = new ArrayList<>();
        for(Hit<Product> hit : listOfHits){
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

    @Override
    public List<Product> fuzzySearch(SearchQuery searchquery) throws IOException {
        SearchResponse<Product> searchResponse =  searchServiceElasticSearch.fuzzySearch(searchquery);
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits= searchResponse.hits().hits();
        List<Product> listOfProducts  = new ArrayList<>();
        for(Hit<Product> hit : listOfHits){
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

    @Override
    public List<Product> priceSearch(SearchQueryWithPrice searchQueryWithPrice) throws IOException {
        SearchResponse<Product> searchResponse =  searchServiceElasticSearch.boolQueryImpl(searchQueryWithPrice);
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits= searchResponse.hits().hits();
        List<Product> listOfProducts  = new ArrayList<>();
        for(Hit<Product> hit : listOfHits){
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }


    @Override
    public Iterable<Product> findAll() {
        return searchServiceElasticSearch.findAll();
    }


}
