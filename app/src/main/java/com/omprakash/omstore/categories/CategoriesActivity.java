package com.omprakash.omstore.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.omprakash.omstore.BaseActivity;
import com.omprakash.omstore.databinding.ActivityCategoriesBinding;
import com.omprakash.omstore.network.FakeStoreApi;
import com.omprakash.omstore.network.FakeStoreService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity {

    ActivityCategoriesBinding binding;
    CategoriesAdapter categoriesAdapter;
    ArrayList<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
        fetchCategories();
        setupCategoriesAdapter();
        setupCategoriesRv();
    }

    private void fetchCategories() {
        FakeStoreApi fakeStoreApi = new FakeStoreApi();
        FakeStoreService fakeStoreService = fakeStoreApi.createCategoryService();
        Call<List<String>> call = fakeStoreService.fetchCategories();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                showToast("Successfully loaded the data");
                List<String> categories = response.body();
                categoriesAdapter.setData(categories);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                showToast("Failed to load the data");
            }
        });
    }

    private void setupCategoriesAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(categories);
    }

    private void setupCategoriesRv() {
        binding.categoriesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }
}