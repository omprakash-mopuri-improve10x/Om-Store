package com.omprakash.omstore.network;

import com.omprakash.omstore.Constants;
import com.omprakash.omstore.products.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeStoreService {

    @GET(Constants.CATEGORY_ENDPOINT)
    Call<List<String>> fetchCategories();

    @GET("/products/category/{categoryName}")
    Call<List<Product>> fetchProducts(@Path("categoryName") String categoryName);
}
