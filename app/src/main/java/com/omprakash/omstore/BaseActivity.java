package com.omprakash.omstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.omprakash.omstore.network.FakeStoreApi;
import com.omprakash.omstore.network.FakeStoreService;

public class BaseActivity extends AppCompatActivity {

    protected FakeStoreService fakeStoreService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFakeStoreApiService();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setupFakeStoreApiService() {
        FakeStoreApi fakeStoreApi = new FakeStoreApi();
        fakeStoreService = fakeStoreApi.createCategoryService();
    }
}
