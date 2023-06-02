package com.omprakash.omstore;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Integer id;
    private Integer userId;
    private String date;

    public ArrayList<CartProduct> cartProduct;

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
