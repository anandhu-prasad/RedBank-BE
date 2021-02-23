package com.javainuse.responses;

import java.sql.Timestamp;
import java.util.Date;

public class ProfileIndividualData {

    private String bloodGroup;
    private String email;
    private Date dob;
    private String phone;
    private String address;
    private String state;
    private String district;
    private String pincode;
    private Timestamp registration_date;
    private Timestamp last_donation_date;
    private int donationMade;
    private int commitmentMade;
    private int drivesAttended;

    public ProfileIndividualData(String bloodGroup, String email, Date dob, String phone, String address, String state, String district, String pincode, Timestamp registration_date, Timestamp last_donation_date, int donationMade, int commitmentMade, int drivesAttended) {
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
        this.donationMade = donationMade;
        this.commitmentMade = commitmentMade;
        this.drivesAttended = drivesAttended;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public int getDonationMade() {
        return donationMade;
    }

    public void setDonationMade(int donationMade) {
        this.donationMade = donationMade;
    }

    public int getCommitmentMade() {
        return commitmentMade;
    }

    public void setCommitmentMade(int commitmentMade) {
        this.commitmentMade = commitmentMade;
    }

    public int getDrivesAttended() {
        return drivesAttended;
    }

    public void setDrivesAttended(int drivesAttended) {
        this.drivesAttended = drivesAttended;
    }
}
