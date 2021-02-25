package com.javainuse.responses;

import java.sql.Timestamp;

public class MyDrivesData {

    private String drive_id;
    private Timestamp start_timestamp;
    private Timestamp end_timestamp;
    private String address;
    private String state;
    private String district;
    private String pincode;
    private Boolean status;
    //have to create a list for the blood groups
    private Boolean aPos;
    private Boolean aNeg;
    private Boolean bPos;
    private Boolean bNeg;
    private Boolean oPos;
    private Boolean oNeg;
    private Boolean abPos;
    private Boolean abNeg;


    public MyDrivesData() {super();
    }

    public MyDrivesData(String drive_id, Timestamp start_timestamp, Timestamp end_timestamp, String address, String state, String district, String pincode, Boolean status, Boolean aPos, Boolean aNeg, Boolean bPos, Boolean bNeg, Boolean oPos, Boolean oNeg, Boolean abPos, Boolean abNeg) {
        this.drive_id = drive_id;
        this.start_timestamp = start_timestamp;
        this.end_timestamp = end_timestamp;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.status = status;
        this.aPos = aPos;
        this.aNeg = aNeg;
        this.bPos = bPos;
        this.bNeg = bNeg;
        this.oPos = oPos;
        this.oNeg = oNeg;
        this.abPos = abPos;
        this.abNeg = abNeg;
    }

    public String getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(String drive_id) {
        this.drive_id = drive_id;
    }

    public Timestamp getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(Timestamp start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public Timestamp getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(Timestamp end_timestamp) {
        this.end_timestamp = end_timestamp;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getaPos() {
        return aPos;
    }

    public void setaPos(Boolean aPos) {
        this.aPos = aPos;
    }

    public Boolean getaNeg() {
        return aNeg;
    }

    public void setaNeg(Boolean aNeg) {
        this.aNeg = aNeg;
    }

    public Boolean getbPos() {
        return bPos;
    }

    public void setbPos(Boolean bPos) {
        this.bPos = bPos;
    }

    public Boolean getbNeg() {
        return bNeg;
    }

    public void setbNeg(Boolean bNeg) {
        this.bNeg = bNeg;
    }

    public Boolean getoPos() {
        return oPos;
    }

    public void setoPos(Boolean oPos) {
        this.oPos = oPos;
    }

    public Boolean getoNeg() {
        return oNeg;
    }

    public void setoNeg(Boolean oNeg) {
        this.oNeg = oNeg;
    }

    public Boolean getAbPos() {
        return abPos;
    }

    public void setAbPos(Boolean abPos) {
        this.abPos = abPos;
    }

    public Boolean getAbNeg() {
        return abNeg;
    }

    public void setAbNeg(Boolean abNeg) {
        this.abNeg = abNeg;
    }
}
