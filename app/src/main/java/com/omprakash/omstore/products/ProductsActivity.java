package com.omprakash.omstore.products;

import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.transition.MaterialSharedAxis;
import com.omprakash.omstore.BaseActivity;
import com.omprakash.omstore.Constants;
import com.omprakash.omstore.ProductDetailsActivity;
import com.omprakash.omstore.databinding.ActivityProductsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends BaseActivity {

    private ActivityProductsBinding binding;
    private ProductsAdapter productsAdapter;
    private ArrayList<Product> products = new ArrayList<>();
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().hasExtra(Constants.KEY_CATEGORY_NAME)) {
            categoryName = getIntent().getStringExtra(Constants.KEY_CATEGORY_NAME);
            getSupportActionBar().setTitle(categoryName);
        }
        setupProductsAdapter();
        setupProductsRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchProducts();
    }

    private void fetchProducts() {
        showProgressBar();
        Call<List<Product>> call = fakeStoreService.fetchProducts(categoryName);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                hideProgressBar();
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
        productsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(int productId) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                intent.putExtra(Constants.KEY_PRODUCT_ID, productId);
                startActivity(intent);
            }
        });
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