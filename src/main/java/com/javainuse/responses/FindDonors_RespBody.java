package com.javainuse.responses;

public class FindDonors_RespBody {
    private String userId;
    private String name;
    private String address;
    private String state;
    private String district;
    private String pincode;
    private String avatar;

    public FindDonors_RespBody(String userId, String name, String address, String state, String district, String pincode, String avatar) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.avatar = avatar;
    }
    public FindDonors_RespBody(){
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "FindDonors_RespBody{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode='" + pincode + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
