package com.SOA.search;

import com.SOA.search.service.SimilarityService;
import com.SOA.search.service.implementation.SimilarityServiceElasticSearchImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    
    /*
     * This part is reserved for Spring beans definitions.
     */

    @Bean
    public SimilarityService similarityService() {
        return new SimilarityServiceElasticSearchImpl();
    }
}
