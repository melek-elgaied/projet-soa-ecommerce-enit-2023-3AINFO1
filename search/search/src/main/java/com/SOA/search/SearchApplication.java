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

	}
}
