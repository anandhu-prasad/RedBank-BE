package com.javainuse.responses;

public class BuyBlood_RespBody {
    private String bbId;
    private String bbName;
    private String email;
    private double price;
    private String phoneNo;

    public BuyBlood_RespBody() {
        super();
    }

    public BuyBlood_RespBody(String bbId, String bbName, String email, double price, String phoneNo) {
        this.bbId = bbId;
        this.bbName = bbName;
        this.email = email;
        this.price = price;
        this.phoneNo = phoneNo;
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

    @Override
    public String toString() {
        return "BuyBlood_RespBody{" +
                "bbId='" + bbId + '\'' +
                ", bbName='" + bbName + '\'' +
                ", email='" + email + '\'' +
                ", price=" + price +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
