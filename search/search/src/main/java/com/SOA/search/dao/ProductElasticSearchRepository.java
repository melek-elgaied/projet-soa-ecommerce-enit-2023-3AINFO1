package com.SOA.search.dao;

import com.SOA.search.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product,Long> {
}
