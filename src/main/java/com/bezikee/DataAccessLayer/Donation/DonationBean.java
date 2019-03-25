package com.bezikee.DataAccessLayer.Donation;

import java.sql.Date;
import java.sql.Timestamp;

public class DonationBean {

    private int id;
    private String name;
    private float amount;
    private String description;
    private String purpose;
    private String currency;
    private String date;
    private String email;
    private String transferNum;
    private String status;

    public DonationBean(int id, String name, float amount, String description, String purpose, String currency, String date, String email, String transferNum, String status) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.purpose = purpose;
        this.currency = currency;
        this.date = date;
        this.email = email;
        this.transferNum = transferNum;
        this.status = status;
    }

    public DonationBean(String name, float amount, String description, String purpose, String currency, String date, String email, String transferNum, String status) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.purpose = purpose;
        this.currency = currency;
        this.date = date;
        this.email = email;
        this.transferNum = transferNum;
        this.status = status;
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


    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransferNum() {
        return transferNum;
    }

    public void setTransferNum(String transferNum) {
        this.transferNum = transferNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
