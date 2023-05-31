package com.omprakash.omstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.omprakash.omstore.databinding.ActivityProductDetailsBinding;
import com.omprakash.omstore.network.FakeStoreApi;
import com.omprakash.omstore.network.FakeStoreService;
import com.omprakash.omstore.products.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity {

    ActivityProductDetailsBinding binding;
    int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        getSupportActionBar().setTitle("Product Details");
        setContentView(binding.getRoot());
        if (getIntent().hasExtra("categoryId")) {
            categoryId = getIntent().getIntExtra("categoryId", 0);
        }
        fetchProduct();
    }

    private void fetchProduct() {
        showProgressBar();
        Call<Product> call = fakeStoreService.fetchProduct(categoryId);
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