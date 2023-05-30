package com.omprakash.omstore.categories;

import com.omprakash.omstore.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryApi {

    public CategoryService createCategoryService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.CATEGORY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CategoryService categoryService = retrofit.create(CategoryService.class);
        return categoryService;
    }
}
