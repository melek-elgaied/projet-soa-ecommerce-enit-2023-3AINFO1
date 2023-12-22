package com.SOA.search.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class ElasticSearchUtil {


    public static Supplier<Query> supplierWithDescriptionField(String fieldValue) {
        return () -> Query.of(q -> q.match(matchQueryWithDescriptionField(fieldValue)));
    }

    public static MatchQuery matchQueryWithDescriptionField(String fieldValue) {
        return new MatchQuery.Builder().field("description").query(fieldValue).build();

    }
}
