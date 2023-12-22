package com.SOA.search.service;

import com.SOA.search.model.Product;

import java.util.List;
import java.util.Map;


//act as an input to index products across all available search services in the system
public interface Index {

    public void index(List<Product> products);

    public Map<Long,Integer> getStat(String word);

    public void removeFromIndex(long id);
}
