package com.javainuse.requests;

public class ProfileUpdateIndRequestBody {

    private String address;
    private String district;
    private String state;
    private String pincode;
    private String phone;
    private String bloodGroup;

    public ProfileUpdateIndRequestBody() {
        super();
    }

    public ProfileUpdateIndRequestBody(String address, String district, String state, String pincode, String phone, String bloodGroup) {
        this.address = address;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "ProfileUpdateIndRequestBody{" +
                "address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", phone='" + phone + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
