package com.javainuse.requests;

import java.sql.Timestamp;

public class ConfirmBuy_ReqBody {
    private String sellerId;
    private String bloodGroup;
    private String component;
    private int units;
    private String reason;
    private String location;

    public ConfirmBuy_ReqBody() {
        super();
    }

    public ConfirmBuy_ReqBody(String sellerId, String bloodGroup, String component, int units, String reason, String location) {
        this.sellerId = sellerId;
        this.bloodGroup = bloodGroup;
        this.component = component;
        this.units = units;
        this.reason = reason;
        this.location = location;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ConfirmBuy_ReqBody{" +
                "sellerId='" + sellerId + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", component='" + component + '\'' +
                ", units=" + units +
                ", reason='" + reason + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
