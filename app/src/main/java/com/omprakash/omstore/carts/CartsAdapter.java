package com.omprakash.omstore.carts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omprakash.omstore.databinding.CartItemBinding;
import com.omprakash.omstore.models.Cart;

import java.util.ArrayList;

public class CartsAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private ArrayList<Cart> carts;

    void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(binding);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
