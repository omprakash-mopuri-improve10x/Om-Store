package com.omprakash.omstore.categories;

import com.omprakash.omstore.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET(Constants.CATEGORY_ENDPOINT)
    Call<List<String>> fetchCategories();
}
