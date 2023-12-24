package com.SOA.search.controller;

import com.SOA.search.controller.api.SimilarityApi;
import com.SOA.search.exception.BadRequestException;
import com.SOA.search.exception.ExceptionSoa;
import com.SOA.search.exception.NotFoundException;
import com.SOA.search.model.Product;
import com.SOA.search.service.SimilarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SimilarityController implements SimilarityApi {

    @Autowired
    private SimilarityService similarityService;

    public List<Product> getSimilarProducts(@RequestParam long id) {
        return similarityService.getSimilarProducts(id);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiResponse apiResponse = new ApiResponse("Bad Request", ex.getMessage(), httpStatus, httpStatus.value());
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiResponse apiResponse = new ApiResponse("Not Found", ex.getMessage(), httpStatus, httpStatus.value());
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @ExceptionHandler(ExceptionSoa.class)
    public ResponseEntity<ApiResponse> handleException(ExceptionSoa ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiResponse apiResponse = new ApiResponse("Internal Server Error", ex.getMessage(), httpStatus, httpStatus.value());
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiResponse apiResponse = new ApiResponse("Internal Server Error", "Internal Server Error", httpStatus, httpStatus.value());
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    static class ApiResponse {
        private String status;
        private String message;
        private HttpStatus apiStatus;
        private Integer code;

        public ApiResponse(String status, String message, HttpStatus apiStatus, Integer code) {
            this.status = status;
            this.message = message;
            this.apiStatus = apiStatus;
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public HttpStatus getApiStatus() {
            return apiStatus;
        }

        Integer getCode(){
            return code;
        }

    }
}
