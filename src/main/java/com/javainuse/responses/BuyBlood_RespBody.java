package com.javainuse.responses;

public class BuyBlood_RespBody {
    private String bbId;
    private String bbName;
    private String email;
    private double price;
    private String phoneNo;
    private String address;
    private String district;
    private String state;
    private String pincode;

    public BuyBlood_RespBody() {
        super();
    }

    public BuyBlood_RespBody(String bbId, String bbName, String email, double price, String phoneNo, String address, String district, String state, String pincode) {
        this.bbId = bbId;
        this.bbName = bbName;
        this.email = email;
        this.price = price;
        this.phoneNo = phoneNo;
        this.address = address;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
    }


    public String getBbId() {
        return bbId;
    }

    public void setBbId(String bbId) {
        this.bbId = bbId;
    }

    public String getBbName() {
        return bbName;
    }

    public void setBbName(String bbName) {
        this.bbName = bbName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    @Override
    public String toString() {
        return "BuyBlood_RespBody{" +
                "bbId='" + bbId + '\'' +
                ", bbName='" + bbName + '\'' +
                ", email='" + email + '\'' +
                ", price=" + price +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
