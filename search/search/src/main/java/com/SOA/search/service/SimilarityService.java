package com.SOA.search.service;

import com.SOA.search.exception.BadRequestException;
import com.SOA.search.exception.NotFoundException;
import com.SOA.search.model.Product;

import java.util.List;


// this service is used to index products and search similar product


public interface SimilarityService {

    public void index(List<Product> products);

    public List<Product> getSimilarProducts(long id) throws BadRequestException, NotFoundException;


}
