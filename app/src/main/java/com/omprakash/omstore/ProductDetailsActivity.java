package com.omprakash.omstore;

import android.os.Bundle;
import android.view.View;

import com.omprakash.omstore.databinding.ActivityProductDetailsBinding;
import com.omprakash.omstore.products.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity {

    private ActivityProductDetailsBinding binding;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Product Details");
        if (getIntent().hasExtra("productId")) {
            productId = getIntent().getIntExtra("productId", 0);
        }
        fetchProduct();
    }

    private void fetchProduct() {
        showProgressBar();
        Call<Product> call = fakeStoreService.fetchProduct(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                hideProgressBar();
                showToast("successfully loaded the data");
                Product product = response.body();
                binding.setProduct(product);
                binding.productRating.setRating(product.rating.getRate());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load the data");
            }
        });
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }
}