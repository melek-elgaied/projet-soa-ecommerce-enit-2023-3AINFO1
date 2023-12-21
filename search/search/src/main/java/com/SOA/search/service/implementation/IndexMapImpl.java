package com.SOA.search.service.implementation;

import com.SOA.search.model.Product;
import com.SOA.search.service.Index;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@Service
public class IndexMapImpl implements Index {

    private Map<String, Map<Long, Integer>> index = new HashMap<>();
    private Map<Long, Product> productMap = new HashMap<>();

    @Override
    public void index(List<Product> products) {
        for (Product product : products){
            Long productId = product.getId();
            productMap.put(productId, product);
            String[] words= product.getDescription().split("\\s+");
            for (String word : words){
                word=word.toLowerCase();
                indexWord(word, product.getId());
            }

        }
    }

    private void indexWord(String word, Long productId) {
        Map<Long, Integer> occurrences = index.computeIfAbsent(word, k -> new HashMap<>());
        occurrences.put(productId, occurrences.getOrDefault(productId, 0) + 1);
    }


    @Override
    public Map<Long, Integer> getStat(String word) {
        word = word.toLowerCase();
        return index.getOrDefault(word, new HashMap<>());

    }
    public Product getProductById(Long productId) {
        return productMap.get(productId);
    }

}
