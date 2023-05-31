package com.omprakash.omstore.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.omprakash.omstore.R;
import com.omprakash.omstore.databinding.ActivityProductsBinding;

public class ProductsActivity extends AppCompatActivity {

    ActivityProductsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().hasExtra("category")) {
            String category = getIntent().getStringExtra("category");
            getSupportActionBar().setTitle(category);
        }
    }
}