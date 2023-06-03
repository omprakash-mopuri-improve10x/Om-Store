package com.omprakash.omstore.products;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.omprakash.omstore.base.BaseActivity;
import com.omprakash.omstore.carts.CartsActivity;
import com.omprakash.omstore.Constants;
import com.omprakash.omstore.databinding.ActivityProductsBinding;
import com.omprakash.omstore.productdetails.ProductDetailsActivity;
import com.omprakash.omstore.R;
import com.omprakash.omstore.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends BaseActivity {

    private ActivityProductsBinding binding;
    private ProductsAdapter productsAdapter;
    private ArrayList<Product> products = new ArrayList<>();
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra(Constants.KEY_CATEGORY_ID)) {
            categoryId = getIntent().getIntExtra(Constants.KEY_CATEGORY_ID, 0);
            getSupportActionBar().setTitle("Products");
        }
        setupProductsAdapter();
        setupProductsRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.cart_img) {
            Intent intent = new Intent(this, CartsActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchProducts() {
        showProgressBar();
        Call<List<Product>> call = fakeStoreService.fetchProducts(categoryId);
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