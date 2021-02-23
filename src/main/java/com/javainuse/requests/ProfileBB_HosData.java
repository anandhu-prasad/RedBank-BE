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
    private int drivesConducted;
    private int salesMade;
    private int requestMade;

    public ProfileBB_HosData() {
        super();
    }

    public ProfileBB_HosData(String email, String license_number, List<String> phone, String address, String state, String district, String pincode, Timestamp registration_date, int drivesConducted, int salesMade, int requestMade) {
        this.email = email;
        this.license_number = license_number;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.registration_date = registration_date;
        this.drivesConducted = drivesConducted;
        this.salesMade = salesMade;
        this.requestMade = requestMade;
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

    public int getDrivesConducted() {
        return drivesConducted;
    }

    public void setDrivesConducted(int drivesConducted) {
        this.drivesConducted = drivesConducted;
    }

    public int getSalesMade() {
        return salesMade;
    }

    public void setSalesMade(int salesMade) {
        this.salesMade = salesMade;
    }

    public int getRequestMade() {
        return requestMade;
    }

    public void setRequestMade(int requestMade) {
        this.requestMade = requestMade;
    }
}

