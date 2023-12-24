package com.SOA.search.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchQueryWithPrice extends SearchQuery {
    private  double minPrice;
    private  double maxPrice;
}



