package com.javainuse.requests;

import java.sql.Timestamp;

public class ConfirmBuy_ReqBody {
    private String sellerId;
    private String bloodGroup;
    private String component;
    private int units;

    public ConfirmBuy_ReqBody() {
        super();
    }

    public ConfirmBuy_ReqBody(String sellerId, String bloodGroup, String component, int units) {
        this.sellerId = sellerId;
        this.bloodGroup = bloodGroup;
        this.component = component;
        this.units = units;
    }


    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }


}
