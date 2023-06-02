package com.omprakash.omstore.products;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omprakash.omstore.databinding.ProductItemBinding;
import com.omprakash.omstore.models.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> products;

    void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding binding = ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(binding);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.setProduct(product);
        holder.binding.getRoot().setOnClickListener(v -> {
            onItemActionListener.onItemClicked(product.getId());
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
