package com.SOA.search.service;

import com.SOA.search.dto.SearchQuery;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.model.Product;

import java.util.List;


// this service is used to index products and search products by text
public interface SearchService {

    //index
    public void index(List<Product> products);
    public List<Product> searchByText(SearchQuery searchquery) throws BadRequestException;

    public Iterable<Product> findAll() ;



}
