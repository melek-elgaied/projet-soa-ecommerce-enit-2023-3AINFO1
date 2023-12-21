package com.SOA.search;

import com.SOA.search.dto.SearchQuery;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.model.Product;
import com.SOA.search.service.implementation.IndexMapImpl;
import com.SOA.search.service.implementation.SearchServiceImplMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);


		// test the SearchServiceImplMap
		/*List<Product> products = new ArrayList<>();
		products.add(new Product(1L,"azeaz", "A   orange is a fruit. Apple is red.",8778.6));
		products.add(new Product(2L,"aze", "apple is another banana fruit. Orange is orange.",8778.6));
		products.add(new Product(3L,"ohjo", "Banana Apple Apple is a apple aplle fruit.",8778.6));

		IndexMapImpl indexMap = new IndexMapImpl();
		indexMap.index(products);


		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setQuery("banana orange apple");
		SearchServiceImplMap search= new SearchServiceImplMap();
		search.setIndex(indexMap);

		try {
			List<Product> searchResults = search.searchByText(searchQuery);
			System.out.println("Search results for query '" + searchQuery + "':");
			for (Product result : searchResults) {
				System.out.println("Product ID: " + result.getId() + ", Description: " + result.getDescription());
			}
			System.out.println();
		} catch (BadRequestException e) {
			System.err.println("Error in search: " + e.getMessage());
		}
*/

	}
}
