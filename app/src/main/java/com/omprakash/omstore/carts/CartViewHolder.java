package com.omprakash.omstore.carts;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omprakash.omstore.databinding.CartItemBinding;

public class CartViewHolder extends RecyclerView.ViewHolder {

    CartItemBinding binding;

    public CartViewHolder(CartItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
