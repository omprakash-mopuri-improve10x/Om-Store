package com.omprakash.omstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Cart {

    private Integer id;
    private Integer userId;
    private String date;

    @SerializedName("products")
    public ArrayList<CartProduct> cartProducts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
