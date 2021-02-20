package com.javainuse.requests;


import javax.persistence.Column;
import java.sql.Date;
import java.sql.Timestamp;

public class ProfileIndData {

    private String bloodGroup;
    private String email;
    private Date dob;
    private int phone;
    private String address;
    private String state;
    private String district;
    private int pincode;
    private Timestamp registration_date;
    private Timestamp last_donation_date;

    public ProfileIndData(String bloodGroup, String email, Date dob, int phone,
                          String address, String state, String district, int pincode,
                          Timestamp registration_date, Timestamp last_donation_date) {
        this.bloodGroup = bloodGroup;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.registration_date = registration_date;
        this.last_donation_date = last_donation_date;

    }

    public ProfileIndData() {
        super();
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
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

}