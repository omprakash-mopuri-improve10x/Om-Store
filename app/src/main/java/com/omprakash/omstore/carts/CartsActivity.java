package com.omprakash.omstore.carts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.omprakash.omstore.base.BaseActivity;
import com.omprakash.omstore.databinding.ActivityCartsBinding;
import com.omprakash.omstore.models.Cart;
import com.omprakash.omstore.models.CartProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartsActivity extends BaseActivity {

    private ActivityCartsBinding binding;
    private CartsAdapter cartsAdapter;
    private ArrayList<CartProduct> carts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Carts");
        getCart();
        setupCartsAdapter();
        setupCartsRv();
    }

    private void getCart() {
        Call<Cart> call = fakeStoreService.getCart();
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    Cart cart = response.body();
                    cartsAdapter.setCarts(cart.getCartProducts());
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
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