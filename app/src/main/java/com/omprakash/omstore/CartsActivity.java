package com.omprakash.omstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.omprakash.omstore.databinding.ActivityCartsBinding;

public class CartsActivity extends AppCompatActivity {

    private ActivityCartsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}