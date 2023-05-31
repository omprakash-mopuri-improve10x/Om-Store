package com.omprakash.omstore.network;

import com.omprakash.omstore.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeStoreApi {

    public FakeStoreService createCategoryService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FakeStoreService fakeStoreService = retrofit.create(FakeStoreService.class);
        return fakeStoreService;
    }
}
