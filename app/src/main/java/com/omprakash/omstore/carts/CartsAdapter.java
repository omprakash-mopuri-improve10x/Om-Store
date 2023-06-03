package com.omprakash.omstore.carts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omprakash.omstore.databinding.CartItemBinding;
import com.omprakash.omstore.models.Cart;
import com.omprakash.omstore.models.CartProduct;

import java.util.ArrayList;
import java.util.List;

public class CartsAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<CartProduct> carts;

    void setCarts(List<CartProduct> carts) {
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
        CartProduct cartProduct = carts.get(position);
        holder.binding.productQuantity.setText(String.valueOf(cartProduct.getQuantity()));
        holder.binding.productPrice.setText(String.valueOf(cartProduct.getProductId()));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
