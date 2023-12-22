package com.SOA.search.service.implementation;

import com.SOA.search.dao.ProductElasticSearchRepository;
import com.SOA.search.model.Product;
import com.SOA.search.service.Index;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndexElasticSearchImpl implements Index {
    private final ProductElasticSearchRepository productElasticSearchRepository;

    public IndexElasticSearchImpl(ProductElasticSearchRepository productElasticSearchRepository) {
        this.productElasticSearchRepository = productElasticSearchRepository;
    }

    @Override
    public void index(List<Product> products) {
        productElasticSearchRepository.saveAll(products);
    }

    public void indexOneProduct(Product product) {
        productElasticSearchRepository.save(product);
    }



    @Override
    public Map<Long, Integer> getStat(String word) {
        return null;
    }
}
