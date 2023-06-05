package com.omprakash.omstore.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.omprakash.omstore.R;
import com.omprakash.omstore.base.BaseActivity;
import com.omprakash.omstore.databinding.ActivitySearchProductsBinding;
import com.omprakash.omstore.models.Product;
import com.omprakash.omstore.products.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductsActivity extends BaseActivity {

    private ActivitySearchProductsBinding binding;

    private ProductsAdapter productsAdapter;

    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupSearchProductsAdapter();
        setupSearchProductsRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        android.widget.SearchView searchView = (android.widget.SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fetchProducts(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void fetchProducts(String s) {
        Call<List<Product>> call = fakeStoreService.fetchProducts(s);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    productsAdapter.setProducts(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                showToast("Failed load data");
            }
        });
    }

    private void setupSearchProductsAdapter() {
        productsAdapter = new ProductsAdapter();
        productsAdapter.setProducts(products);
    }

    private void setupSearchProductsRv() {
        binding.searchProductsRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.searchProductsRv.setAdapter(productsAdapter);
    }
}