package com.SOA.search.service.implementation;

import com.SOA.search.model.Product;
import com.SOA.search.service.Index;

import java.util.List;
import java.util.Map;

public class IndexMapImpl implements Index {

    private Map<String,Map<Long, Integer>> index;
    @Override
    public void index(List<Product> products) {

    }

    @Override
    public Map<Long, Integer> getStat() {
        return null;
    }
}
