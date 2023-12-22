package com.SOA.search.service.implementation;

import com.SOA.search.dto.SearchQuery;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.model.Product;
import com.SOA.search.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImplMap implements SearchService {

    private IndexMapImpl index;

    public void setIndex(IndexMapImpl index) {
        this.index = index;
    }

    @Override
    public void index(List<Product> products) {
        this.index.index(products);
    }

    @Override
    public List<Product> searchByText(SearchQuery searchQuery) throws BadRequestException {
        String[] queryWords = searchQuery.getQuery().split("\\s+");

        List<Product> matchingProducts = new ArrayList<>();
        Set<Long> uniqueProductIds = new HashSet<>();

        for (String queryWord : queryWords) {
            Map<Long, Integer> occurrences = index.getStat(queryWord.toLowerCase());
                for (Map.Entry<Long, Integer> entry : occurrences.entrySet()) {
                Long productId = entry.getKey();
                if (uniqueProductIds.add(productId)) {
                    Product product = index.getProductById(productId);
                    matchingProducts.add(product);
                }
            }
        }

        matchingProducts.sort(Comparator
                .<Product, Boolean>comparing(product -> containsAllWords(product.getDescription().toLowerCase(), queryWords))
                .thenComparingInt(product -> countMatchingWords(product.getDescription().toLowerCase(), queryWords))
                .thenComparingInt(product -> getTotalOccurrences(product, queryWords))
                .reversed());

        return matchingProducts;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    private int countMatchingWords(String description, String[] queryWords) {
        int count = 0;
        for (String word : queryWords) {
            if (description.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    private boolean containsAllWords(String description, String[] queryWords) {
        for (String word : queryWords) {
            if (!description.contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    private int getTotalOccurrences(Product product, String[] queryWords) {
        int totalOccurrences = 0;
        String description = product.getDescription().toLowerCase();

        for (String word : queryWords) {
            int index = description.indexOf(word.toLowerCase());
            while (index != -1) {
                totalOccurrences++;
                index = description.indexOf(word.toLowerCase(), index + 1);
            }
        }

        return totalOccurrences;
    }
}
