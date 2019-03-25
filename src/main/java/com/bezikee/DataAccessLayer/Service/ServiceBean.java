package com.bezikee.DataAccessLayer.Service;

import java.sql.Date;

public class ServiceBean {

    private int id;
    private String name;
    private String currency;
    private float basePrice;
    private String category;

    public ServiceBean(int id, String name, String currency, float basePrice, String category) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.basePrice = basePrice;
        this.category = category;
    }

    public ServiceBean(String name, String currency, float basePrice, String category) {
        this.name = name;
        this.currency = currency;
        this.basePrice = basePrice;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
