package com.SOA.search.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ElasticSearchUtil {


    public static Supplier<Query> supplierWithNameField(String fieldValue) {
        return () -> Query.of(q -> q.match(matchQueryWithNameField(fieldValue)));
    }

    public static MatchQuery matchQueryWithNameField(String fieldValue) {
        return new MatchQuery.Builder().field("name").query(fieldValue).build();

    }

    //// FuzzySearch

    public static Supplier<Query> createSupplierQuery(String approximateProductName) {
        String[] words = approximateProductName.split("\\s+");

        return () -> {
            Query.Builder builder = new Query.Builder();
            for (String word : words) {
                builder.fuzzy(createFuzzyQuery(word));
            }
            return builder.build();
        };
    }


    public static FuzzyQuery createFuzzyQuery(String approximateProductName){
        val fuzzyQuery = new FuzzyQuery.Builder();
        return fuzzyQuery.field("description").value(approximateProductName).build();

    }

    /// bool query

    public static Supplier<Query> supplierQueryBool(String productName,double minPrice, double maxPrice){
        Supplier<Query> supplier = ()->Query.of(q->q.bool(boolQuery(productName,minPrice,maxPrice)));
        return supplier;
    }



    public static BoolQuery boolQuery(String productName, double minPrice, double maxPrice) {
        return new BoolQuery.Builder()
                .filter(termQuery(productName))
                .must(List.of(rangeQuery("price", minPrice, maxPrice)._toQuery()))
                .build();
    }


    public static List<Query> termQuery(String productName) {
        final List<Query> terms = new ArrayList<>();
        val termQuery = new TermQuery.Builder();
        terms.add(Query.of(q -> q.term(termQuery.field("description").value(productName).build())));
        return terms;
    }

    public static RangeQuery rangeQuery(String field, double min, double max) {
        return new RangeQuery.Builder()
                .field(field)
                .gte(JsonData.of(min))
                .lte(JsonData.of(max))
                .build();
    }




}
