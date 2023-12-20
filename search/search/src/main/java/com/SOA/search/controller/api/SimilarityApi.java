    package com.SOA.search.controller.api;

    import com.SOA.search.model.Product;
    import com.SOA.search.utils.Constants;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiResponse;
    import io.swagger.annotations.ApiResponses;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;

    import java.util.List;


    @Api("search by similarity")
    public interface SimilarityApi {

        @GetMapping(value = Constants.SEARCH_BY_SIMILARITY_ENDPOINT)
        @ApiResponses(value = {
                @ApiResponse(code = 200,message = "fetched similar product "),
                @ApiResponse(code = 400, message = "Bad Request "),
                @ApiResponse(code = 404, message = "No product with this id is indexed"),
        })
        public List<Product> getSimilarProducts(@PathVariable long id);
    }
