package com.SOA.search.service.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.exception.ExceptionSoa;
import com.SOA.search.exception.NotFoundException;
import com.SOA.search.model.Product;
import com.SOA.search.service.SimilarityService;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.log4j.Log4j2;

/**
 * SimilarityServiceElasticSearchImpl is an implementation of the
 * {@link SimilarityService} interface
 * that utilizes Elasticsearch for indexing and searching products based on
 * their similarity.
 *
 * This service provides functionalities to index a list of products and
 * retrieve a list of similar
 * products based on the description of a given product. Elasticsearch is used
 * as the underlying
 * storage and search engine to efficiently manage and query product data.
 *
 * Elasticsearch Configuration:
 * - The Elasticsearch connection is established during the initialization of
 * the service.
 * - If the specified index does not exist, it is created and configured
 * automatically.
 *
 * Elasticsearch Parameters:
 * - The Elasticsearch index name is set to "similarity".
 * - Maximum allowed response elements and maximum elements in a single index
 * request are configured
 * through {@code maxNumberOfResponseElements} and
 * {@code maxNumberOfElementsInIndexRequest}.
 *
 * Logging:
 * - Logging is performed using Log4j2. Errors and important information related
 * to Elasticsearch
 * operations are logged for monitoring and debugging purposes.
 *
 * @author Omar Romdhane.
 * @version 1.0
 */

@Log4j2
public class SimilarityServiceElasticSearchImpl implements SimilarityService {

    /*
     * Declaring elasticsearch parameters and utilities.
     */
    private String elasticIndexName = "similarity";
    private ElasticsearchClient esClient;
    private final String serverUrl = "http://localhost:9200";


    /*
     * Declaring similarity service configurations.
     */
    private final Integer maxNumberOfResponseElements = 10;
    private final Integer maxNumberOfElementsInIndexRequest = 10;


    public SimilarityServiceElasticSearchImpl() {
        initializeElasticSearchConnection();
    }


    /**
     * Initializes the Elasticsearch connection and creates the index if it doesn't
     * exist.
     */
    private void initializeElasticSearchConnection() {
        log.info("Initializing elsticsearch connection within SimilarityServiceElasticSearchImpl class.");
        try {
            RestClient restClient = RestClient
                    .builder(HttpHost.create(serverUrl))
                    .build();

            ElasticsearchTransport transport = new RestClientTransport(
                    restClient, new JacksonJsonpMapper());

            esClient = new ElasticsearchClient(transport);

            boolean indexExist = indexExistsInElasticSearch();
            if (!indexExist) {
                createElasticSearchIndex(elasticIndexName);
            }
        } catch (Exception e) {
            log.fatal("Failed to initialize Elasticsearch within the SimilarityServiceElasticSearchImpl service class.",
                    e);
            throw new RuntimeException(
                    "Failed to initialize Elasticsearch within the SimilarityServiceElasticSearchImpl service class.",
                    e);
        }
    }


    /**
     * Checks if the Elasticsearch index exists.
     *
     * @return true if the index exists, false otherwise.
     * @throws IOException if an I/O exception occurs during the Elasticsearch
     *                     request.
     */
    private boolean indexExistsInElasticSearch() throws IOException {
        boolean answer = false;
        BooleanResponse result = esClient.indices().exists(ExistsRequest.of(e -> e.index(elasticIndexName)));
        answer = result.value();
        return answer;
    }


    /**
     * Creates an Elasticsearch index with the specified name.
     *
     * @param indexName the name of the index to be created.
     * @throws IOException if an I/O exception occurs during the Elasticsearch
     *                     request.
     */
    private void createElasticSearchIndex(String indexName) throws IOException {
        esClient.indices().create(c -> c
                .index(indexName));
    }


    /**
     * Retrieves a product from Elasticsearch by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return the retrieved product or null if not found.
     */
    private Product getProductByIdElasticSearch(Long id) {
        Product res = null;
        String idString = String.valueOf(id);
        try {
            GetResponse<Product> response = esClient.get(g -> g
                    .index(elasticIndexName)
                    .id(idString),
                    Product.class);

            if (response.found()) {
                res = response.source();
            }
        } catch (ExceptionSoa ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching product by id.");
        }
        return res;
    }


    /**
     * Removes a product from Elasticsearch.
     *
     * @param id ID of the product to be removed.
     * @throws IOException if an I/O exception occurs during the Elasticsearch
     *                     request.
     */
    private void removeProductElasticSearch(Long id) throws IOException {
        esClient.delete(d -> d.index(elasticIndexName).id(String.valueOf(id)));
    }


    /**
     * Indexes a list of products in Elasticsearch.
     *
     * @param products the list of products to be indexed.
     * @throws BadRequestException      if the number of products exceeds the
     *                                  maximum allowed for indexing or some of the
     *                                  required attributes are missing.
     * @throws ExceptionSoa             if an error occurs during indexing.
     * @throws IllegalArgumentException if the products list is null.
     */
    @Override
    public void index(List<Product> products) {

        if (products == null) {
            throw new IllegalArgumentException("Products list should not be null.");
        }

        if (products.size() == 0) {
            return;
        }

        if (products.size() > maxNumberOfElementsInIndexRequest) {
            throw new BadRequestException(
                    "Number of products exceeds the maximum allowed for indexing. The maximum allowed number is "
                            + maxNumberOfElementsInIndexRequest);
        }

        BulkRequest.Builder br = new BulkRequest.Builder();
        List<Product> failedProducts = new ArrayList<>();

        for (Product product : products) {
            if (!isValidProduct(product)) {
                throw new BadRequestException(
                        "Id and description cannot be null or empty. Product {" + product + "} is invalid.");
            }
            br.operations(op -> op
                    .index(idx -> idx
                            .index(elasticIndexName)
                            .id(product.getId().toString())
                            .document(product)));
        }

        try {
            BulkResponse bulkResponse = esClient.bulk(br.build());
            if (bulkResponse.errors()) {
                log.error("Error(s) occurred while indexing certain products.");
                for (BulkResponseItem item : bulkResponse.items()) {
                    if (item.error() != null) {
                        Product failedProduct = products.stream()
                                .filter(product -> product.getId() == Integer.parseInt(item.id()))
                                .findFirst()
                                .orElse(null);
                        log.error("Error for product {}: {}", failedProduct, item.error().reason());
                        failedProducts.add(failedProduct);
                    }
                }
                String errorMessage = "Error(s) occurred while indexing products. Failed products: " + failedProducts;
                throw new ExceptionSoa(errorMessage);
            }
        } catch (ExceptionSoa ex) {
            throw ex;
        } catch (Exception e) {
            log.error("Error occurred while indexing products.", e);
            throw new ExceptionSoa("Error occurred while indexing products.");
        }

    }


    /**
     * Retrieves a list of similar products based on the description of a given
     * product.
     *
     * @param id the ID of the product for which similar products are to be
     *           retrieved.
     * @return a list of similar products ordered by their relevance (index 0 is the
     *         most relevant ect ...).
     * @throws BadRequestException if the requested product is not found or an error
     *                             occurs during the search.
     * @throws NotFoundException   if the requested product is not found.
     * @throws ExceptionSoa        if an error occurs during the retrieval of
     *                             similar products.
     */
    @Override
    public List<Product> getSimilarProducts(long id) throws BadRequestException, NotFoundException {
        List<Product> answer = new ArrayList<>();
        try {
            Product requestedProduct = getProductByIdElasticSearch(id);
            if (requestedProduct == null) {
                throw new NotFoundException("No product with id {" + id + "} was found.");
            }
            SearchResponse<Product> response = esClient.search(s -> s
                    .index(elasticIndexName)
                    .query(q -> q
                            .match(t -> t
                                    .field("description")
                                    .query(requestedProduct.getDescription())

                            ))
                    .size(maxNumberOfResponseElements),
                    Product.class);
            List<Hit<Product>> hits = response.hits().hits();
            for (Hit<Product> hit : hits) {
                Product similarProduct = hit.source();
                if (similarProduct.getId() != requestedProduct.getId()) {
                    answer.add(similarProduct);
                }
            }
        } catch (ExceptionSoa ex) {
            throw ex;
        } catch (Exception e) {
            log.error("Error while fetching similar products.", e);
            throw new ExceptionSoa("Error while fetching similar products.");
        }
        return answer;
    }


    /**
     * Removes a product from the Elasticsearch index.
     *
     * @param id the ID of the product to be removed.
     * @throws IllegalArgumentException if the ID is null.
     * @throws RuntimeException         if an error occurs during the removal.
     */
    public void removeFromIndex(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null.");
        }
        try {
            removeProductElasticSearch(id);
        } catch (Exception e) {
            log.error("Error while fetching product with id {" + id + "}");
            throw new RuntimeException(e);
        }
    }


    /**
     * Reindexes a product in Elasticsearch.
     *
     * @param product the product to be indexed.
     * @throws BadRequestException if some of the required attributes are missing or
     *                             if the product does not exist in the
     *                             elasticsearch index.
     * @throws RuntimeException    if an error occurs during reindexing.
     */
    public void reindex(Product product) {
        try {
            if (!isValidProduct(product)) {
                throw new BadRequestException("Id and description cannot be null or empty.");
            }
            Product productToReindex = getProductByIdElasticSearch(product.getId());
            if (productToReindex == null) {
                throw new BadRequestException(
                        "Product with id {" + product.getId() + "} is was not found. Can't reindex product.");
            }
            IndexResponse response = esClient.index(i -> i
                    .index(elasticIndexName)
                    .id(product.getId().toString())
                    .document(product));
        } catch (Exception e) {
            log.error("Error accured while reindexing a product {" + product + "}", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * Validates a product. Basically the id and description fields should not be
     * nor empty or null.
     *
     * @param product product to validate.
     * @return wether the product is valid or not.
     */
    private boolean isValidProduct(Product product) {
        boolean answer = true;
        if (product.getId() == null || product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            answer = false;
        }
        return answer;
    }


}
