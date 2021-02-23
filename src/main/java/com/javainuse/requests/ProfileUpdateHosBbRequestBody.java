package com.javainuse.requests;

import java.util.List;

public class ProfileUpdateHosBbRequestBody {
    private String address;
    private String district;
    private String state;
    private String pincode;
    private List<String> phone;

    public ProfileUpdateHosBbRequestBody() {
        super();
    }

    public ProfileUpdateHosBbRequestBody(String address, String district, String state, String pincode, List<String> phone) {
        this.address = address;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ProfileUpdateHosBbRequestBody{" +
                "address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", phone=" + phone +
                '}';
    }
}
