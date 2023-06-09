package com.omprakash.omstore.network;

import com.omprakash.omstore.models.Cart;
import com.omprakash.omstore.Constants;
import com.omprakash.omstore.models.CartProduct;
import com.omprakash.omstore.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeStoreService {

    @GET(Constants.CATEGORY_ENDPOINT)
    Call<List<String>> fetchCategories();

    @GET("products/category/{categoryName}")
    Call<List<Product>> fetchProducts(@Path("categoryName") String categoryName);

    @GET("products/{productId}")
    Call<Product> fetchProduct(@Path("productId") int productId);

    @GET("carts/1?userId=1")
    Call<Cart> getCart();
}
