package com.omprakash.omstore.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omprakash.omstore.BaseActivity;
import com.omprakash.omstore.Constants;
import com.omprakash.omstore.databinding.ActivityCategoriesBinding;
import com.omprakash.omstore.network.FakeStoreApi;
import com.omprakash.omstore.network.FakeStoreService;
import com.omprakash.omstore.products.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity {

    private ActivityCategoriesBinding binding;
    private CategoriesAdapter categoriesAdapter;
    private ArrayList<String> categories = new ArrayList<>();

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
        showProgressBar();
        Call<List<String>> call = fakeStoreService.fetchCategories();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                hideProgressBar();
                List<String> categories = response.body();
                categoriesAdapter.setData(categories);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load the data");
            }
        });
    }

    private void setupCategoriesAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(categories);
        categoriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(String categoryName) {
                Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
                intent.putExtra(Constants.KEY_CATEGORY_NAME, categoryName);
                startActivity(intent);
            }
        });
    }

    private void setupCategoriesRv() {
        binding.categoriesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }
}