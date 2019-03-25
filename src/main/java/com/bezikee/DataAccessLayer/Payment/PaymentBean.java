package com.bezikee.DataAccessLayer.Payment;

import java.sql.Date;
import java.sql.Timestamp;

public class PaymentBean {


    private int id;
    private int serviceId;
    private String name;
    private int personalId;
    private float amount;
    private String date;
    private String transferNum;
    private String email;
    private String status;
    private String serviceName;
    private String currency;
    private String category;

    public PaymentBean(int id, int serviceId, String name, int personalId, float amount, String date, String transferNum, String email, String status) {
        this.id = id;
        this.serviceId = serviceId;
        this.name = name;
        this.personalId = personalId;
        this.amount = amount;
        this.date = date;
        this.transferNum = transferNum;
        this.email = email;
        this.status = status;
    }

    public PaymentBean(int serviceId, String name, int personalId, float amount, String date, String transferNum, String email, String status) {
        this.serviceId = serviceId;
        this.name = name;
        this.personalId = personalId;
        this.amount = amount;
        this.date = date;
        this.transferNum = transferNum;
        this.email = email;
        this.status = status;
    }

    public PaymentBean(int id, int serviceId, String name, int personalId, float amount, String date, String transferNum, String email, String status, String serviceName, String currency, String category) {
        this.id = id;
        this.serviceId = serviceId;
        this.name = name;
        this.personalId = personalId;
        this.amount = amount;
        this.date = date;
        this.transferNum = transferNum;
        this.email = email;
        this.status = status;
        this.serviceName = serviceName;
        this.currency = currency;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonalId() {
        return personalId;
    }

    public void setPersonalId(int personalId) {
        this.personalId = personalId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransferNum() {
        return transferNum;
    }

    public void setTransferNum(String transferNum) {
        this.transferNum = transferNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
