package com.javainuse.model;

public class ProfileIndDTO {
    private String name;
    private String email;
    private int phone;
    private String dob;
    private String bloodGroup;
    private String address;
    private String state;
    private String district;
    private int pincode;
    private String password;

    public ProfileIndDTO() {
        super();
    }

    public ProfileIndDTO(String name, String email, int phone, String dob, String bloodGroup, String address, String state, String district, int pincode, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
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

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ProfileIndDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", dob='" + dob + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode=" + pincode +
                ", password='" + password + '\'' +
                '}';
    }
}
