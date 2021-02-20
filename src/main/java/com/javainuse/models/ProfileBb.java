package com.javainuse.models;

import com.javainuse.util.StringSequenceIdentifier;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "blood_banks")
public class ProfileBb {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_banks_seq")
    @GenericGenerator(name = "blood_banks_seq", strategy = "com.javainuse.util.StringSequenceIdentifier", parameters = {
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.VALUE_PREFIX_PARAMETER, value = "BOB"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    @Column(name="user_id")
    private String userId;

    private String name;

    private String email;
    private String license_number;
    private int phone1;
    private int phone2;
    private int phone3;
    private int phone4;
    private int phone5;
    private String address;
    private String state;
    private String district;
    private int pincode;
    private String password;
    private Timestamp registration_date;


    public ProfileBb() {
        super();
    }

    public ProfileBb(String name, String email, String license_number, String address, String state, String district, int pincode, String password, Timestamp registration_date) {
        this.name = name;
        this.email = email;
        this.license_number = license_number;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.password = password;
        this.registration_date = registration_date;
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

    public int getPhone1() {
        return phone1;
    }

    public void setPhone1(int phone1) {
        this.phone1 = phone1;
    }

    public int getPhone2() {
        return phone2;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    public int getPhone3() {
        return phone3;
    }

    public void setPhone3(int phone3) {
        this.phone3 = phone3;
    }

    public int getPhone4() {
        return phone4;
    }

    public void setPhone4(int phone4) {
        this.phone4 = phone4;
    }

    public int getPhone5() {
        return phone5;
    }

    public void setPhone5(int phone5) {
        this.phone5 = phone5;
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

    public Timestamp getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Timestamp registration_date) {
        this.registration_date = registration_date;
    }

}
