package com.omprakash.omstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.omprakash.omstore.categories.CategoriesActivity;
import com.omprakash.omstore.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        handleSplash();
    }

    private void handleSplash() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}