package com.SOA.search.service.implementation;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.exception.NotFoundException;
import com.SOA.search.model.Product;
import com.SOA.search.service.SimilarityService;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class SimilarityServiceElasticSearchImpl implements SimilarityService {

    private String index;
    private ElasticsearchClient esClient;
    private final String serverUrl = "http://localhost:9200";

    public SimilarityServiceElasticSearchImpl() {
        initializeElasticSearchConnection();
    }

    private void initializeElasticSearchConnection() {
        try {
            RestClient restClient = RestClient
                    .builder(HttpHost.create(serverUrl))
                    .build();

            // Create the transport with a Jackson mapper
            ElasticsearchTransport transport = new RestClientTransport(
                    restClient, new JacksonJsonpMapper());

            // And create the API client
            esClient = new ElasticsearchClient(transport);

            boolean indexExist = indexExists();
            if (!indexExist) {
                createIndex(index);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Elasticsearch within the SimilarityServiceElasticSearchImpl service class.",e);
        }
    }

    private boolean indexExists() throws IOException {
        boolean answer = false;
        BooleanResponse result = esClient.indices().exists(ExistsRequest.of(e -> e.index(index)));
        answer = result.value();
        return answer;

    }

    private void createIndex(String indexName) throws IOException {
        esClient.indices().create(c -> c
                .index(indexName));
    }

    @Override
    public void index(List<Product> products) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSimilarProducts'");
    }

    @Override
    public List<Product> getSimilarProducts(long id) throws BadRequestException, NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSimilarProducts'");
    }

}
