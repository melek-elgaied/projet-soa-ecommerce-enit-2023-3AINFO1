package com.SOA.search.service.implementation;

import com.SOA.search.dto.SearchQuery;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.model.Product;
import com.SOA.search.service.SearchService;

import java.util.List;

public class SearchServiceImplMap implements SearchService {


    @Override
    public void index(List<Product> products) {

    }

    @Override
    public List<Product> searchByText(SearchQuery searchquery) throws BadRequestException {
        return null;
    }
}
