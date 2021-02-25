package com.javainuse.requests;

public class BuyBlood_ReqBody {
    private String bloodGroup;
    private String component;
    private int reqUnits;
    private String state;
    private String district;
    private String pincode;

    public BuyBlood_ReqBody() {
        super();
    }

    public BuyBlood_ReqBody(String bloodGroup, String component, int reqUnits, String state, String district, String pincode) {
        this.bloodGroup = bloodGroup;
        this.component = component;
        this.reqUnits = reqUnits;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
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

    public int getReqUnits() {
        return reqUnits;
    }

    public void setReqUnits(int reqUnits) {
        this.reqUnits = reqUnits;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "BuyBlood_ReqBody{" +
                "bloodGroup='" + bloodGroup + '\'' +
                ", component='" + component + '\'' +
                ", reqUnits=" + reqUnits +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
