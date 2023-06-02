package com.omprakash.omstore.carts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.omprakash.omstore.base.BaseActivity;
import com.omprakash.omstore.databinding.ActivityCartsBinding;
import com.omprakash.omstore.models.Cart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartsActivity extends BaseActivity {

    private ActivityCartsBinding binding;
    private CartsAdapter cartsAdapter;
    private ArrayList<Cart> carts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getCart();
        setupCartsAdapter();
        setupCartsRv();
    }

    private void getCart() {
        Call<List<Cart>> call = fakeStoreService.getCart();
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    List<Cart> carts = response.body();
                    cartsAdapter.setCarts(carts);
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                showToast("Failed to load data");
            }
        });
    }

    private void setupCartsAdapter() {
        cartsAdapter = new CartsAdapter();
        cartsAdapter.setCarts(carts);
    }

    private void setupCartsRv() {
        binding.cartsRv.setLayoutManager(new LinearLayoutManager(this));
        binding.cartsRv.setAdapter(cartsAdapter);
    }
}