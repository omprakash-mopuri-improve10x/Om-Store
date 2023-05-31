package com.omprakash.omstore.network;

import com.omprakash.omstore.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FakeStoreService {

    @GET(Constants.CATEGORY_ENDPOINT)
    Call<List<String>> fetchCategories();
}
