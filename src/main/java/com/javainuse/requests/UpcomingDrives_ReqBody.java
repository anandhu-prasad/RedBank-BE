package com.javainuse.requests;

public class UpcomingDrives_ReqBody {
    private String state;
    private String district;
    private String pincode;

    public UpcomingDrives_ReqBody() {
        super();
    }

    public UpcomingDrives_ReqBody(String state, String district, String pincode) {
        this.state = state;
        this.district = district;
        this.pincode = pincode;
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
        return "UpcomingDrives_ReqBody{" + "state='" + state + '\'' + ", district='" + district + '\'' + ", pincode='"
                + pincode + '\'' + '}';
    }
}
