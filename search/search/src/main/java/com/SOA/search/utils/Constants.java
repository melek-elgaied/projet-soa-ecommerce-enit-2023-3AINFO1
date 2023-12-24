package com.SOA.search.utils;

public interface Constants {

    String APP_ROOT = "searchservices/api/v1";

    String SEARCH_BY_TEXT_ENDPOINT = APP_ROOT + "/searchbytext";
    String FIND_ALL = APP_ROOT + "/findall";
    String SEARCH_BY_ID= APP_ROOT + "/findbyid/{id}";
    String SEARCH_BY_NAME= APP_ROOT + "/findbyname/{name}";

    String FUZZY_SEARCH=APP_ROOT + "/fuzzysearch";

    String PRICE_SEARCH= APP_ROOT + "/findbyprice";

    String SEARCH_BY_SIMILARITY_ENDPOINT = APP_ROOT + "/searchbysimilarity";
    String INDEX_ENDPOINT = APP_ROOT + "/index";
    String REINDEX_ENDPOINT = APP_ROOT + "/reindex";
    String REMOVE_FROM_INDEX_ENDPOINT = APP_ROOT + "/remove";





}
