package com.omprakash.omstore.network;

import com.omprakash.omstore.models.Cart;
import com.omprakash.omstore.Constants;
import com.omprakash.omstore.models.CartProduct;
import com.omprakash.omstore.models.Category;
import com.omprakash.omstore.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FakeStoreService {

    @GET(Constants.CATEGORY_ENDPOINT)
    Call<List<Category>> fetchCategories();

    @GET("api/v1/products/")
    Call<List<Product>> fetchProducts(@Query("categoryId") int categoryId);

    @GET("api/v1/products/{productId}")
    Call<Product> fetchProduct(@Path("productId") int productId);

    @GET("carts/1?userId=1")
    Call<Cart> getCart();

    @GET("api/v1/products/")
    Call<List<Product>> fetchProducts(@Query("title") String title);
}
