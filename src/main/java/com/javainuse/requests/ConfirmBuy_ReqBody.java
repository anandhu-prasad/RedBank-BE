package com.javainuse.requests;

import java.sql.Timestamp;

public class ConfirmBuy_ReqBody {
    private String customerId;
    private String sellerId;
    private Timestamp date;
    private String bloodGroup;
    private String component;
    private double price;
    private int units;

    public ConfirmBuy_ReqBody() {
        super();
    }

    public ConfirmBuy_ReqBody(String customerId, String sellerId, Timestamp date, String bloodGroup, String component, double price, int units) {
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.date = date;
        this.bloodGroup = bloodGroup;
        this.component = component;
        this.price = price;
        this.units = units;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "ConfirmBuy_ReqBody{" +
                "customerId='" + customerId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", date=" + date +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", component='" + component + '\'' +
                ", units=" + units +
                '}';
    }
}
