package com.omprakash.omstore;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.omprakash.omstore.models.Cart;
import com.omprakash.omstore.models.CartProduct;
import com.omprakash.omstore.network.FakeStoreApi;
import com.omprakash.omstore.network.FakeStoreService;
import com.omprakash.omstore.models.Product;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getCategories() throws IOException {
        FakeStoreApi fakeStoreApi = new FakeStoreApi();
        FakeStoreService fakeStoreService = fakeStoreApi.createCategoryService();
        Call<List<String>> call = fakeStoreService.fetchCategories();
        List<String> categories = call.execute().body();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        System.out.println(new Gson().toJson(categories));
    }

    @Test
    public void getProducts() throws IOException {
        FakeStoreApi fakeStoreApi = new FakeStoreApi();
        FakeStoreService fakeStoreService = fakeStoreApi.createCategoryService();
        Call<List<Product>> call = fakeStoreService.fetchProducts("jewelery");
        List<Product> products = call.execute().body();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        System.out.println(new Gson().toJson(products));
    }

    @Test
    public void getProduct() throws IOException {
        FakeStoreApi fakeStoreApi = new FakeStoreApi();
        FakeStoreService fakeStoreService = fakeStoreApi.createCategoryService();
        Call<Product> call = fakeStoreService.fetchProduct(1);
        Product products = call.execute().body();
        assertNotNull(products);
        System.out.println(new Gson().toJson(products));
    }

    @Test
    public void getCart() throws IOException {
        FakeStoreApi fakeStoreApi = new FakeStoreApi();
        FakeStoreService fakeStoreService = fakeStoreApi.createCategoryService();
        Call<Cart> call = fakeStoreService.getCart();
        Cart cart = call.execute().body();
        assertNotNull(cart);
        System.out.println(new Gson().toJson(cart));
    }
}