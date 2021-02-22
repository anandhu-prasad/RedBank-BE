package com.javainuse.requests;


import java.sql.Timestamp;
import java.util.List;

public class ProfileBB_HosData {

    private String email;
    private String license_number;
    private List<String> phone;
    private String address;
    private String state;
    private String district;
    private String pincode;
    private Timestamp registration_date;
    private Timestamp last_donation_date;

    public ProfileBB_HosData() {
        super();
    }

    public ProfileBB_HosData(String email, String license_number, List<String> phone, String address, String state, String district, String pincode, Timestamp registration_date, Timestamp last_donation_date) {
        this.email = email;
        this.license_number = license_number;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.registration_date = registration_date;
        this.last_donation_date = last_donation_date;
    }

    public ProfileBB_HosData(String email, String license_number, String phone1, String phone2, String phone3, String phone4, String phone5, String address, String state, String district, String pincode, Timestamp registration_date) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
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

    public Timestamp getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Timestamp registration_date) {
        this.registration_date = registration_date;
    }

    public Timestamp getLast_donation_date() {
        return last_donation_date;
    }

    public void setLast_donation_date(Timestamp last_donation_date) {
        this.last_donation_date = last_donation_date;
    }

    @Override
    public String toString() {
        return "ProfileBB_HosData{" +
                "email='" + email + '\'' +
                ", license_number='" + license_number + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode=" + pincode +
                ", registration_date=" + registration_date +
                ", last_donation_date=" + last_donation_date +
                '}';
    }
}

