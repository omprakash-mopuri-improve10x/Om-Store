package com.omprakash.omstore.categories;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omprakash.omstore.databinding.CategoryItemBinding;
import com.omprakash.omstore.products.ProductsActivity;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<String> categories;

    void setData(List<String> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(binding);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.binding.categoryName.setText(categories.get(position));
        holder.binding.getRoot().setOnClickListener(v -> {
            onItemActionListener.onItemClicked(categories.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
