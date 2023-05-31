package com.omprakash.omstore.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omprakash.omstore.BaseActivity;
import com.omprakash.omstore.R;
import com.omprakash.omstore.databinding.ActivityProductsBinding;
import com.omprakash.omstore.network.FakeStoreApi;
import com.omprakash.omstore.network.FakeStoreService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends BaseActivity {

    ActivityProductsBinding binding;
    ProductsAdapter productsAdapter;
    ArrayList<Product> products = new ArrayList<>();
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().hasExtra("category")) {
            category = getIntent().getStringExtra("category");
            getSupportActionBar().setTitle(category);
        }
        fetchProducts();
        setupProductsAdapter();
        setupProductsRv();
    }

    private void fetchProducts() {
        showProgressBar();
        Call<List<Product>> call = fakeStoreService.fetchProducts(category);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                hideProgressBar();
                showToast("Successfully loaded the data");
                List<Product> products = response.body();
                productsAdapter.setProducts(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load the data");
            }
        });
    }

    private void setupProductsAdapter() {
        productsAdapter = new ProductsAdapter();
        productsAdapter.setProducts(products);
    }

    private void setupProductsRv() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.productsRv.setAdapter(productsAdapter);
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }
}