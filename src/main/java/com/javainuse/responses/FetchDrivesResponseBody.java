package com.javainuse.responses;

import java.sql.Timestamp;
import java.util.List;

public class FetchDrivesResponseBody {
    private String driveId;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private String address;
    private String state;
    private String district;
    private String pincode;
    private List<String> bloodGroups;
    private Boolean status;

    public FetchDrivesResponseBody() {
        super();
    }

    public FetchDrivesResponseBody(String driveId, Timestamp startTimestamp, Timestamp endTimestamp, String address, String state, String district, String pincode, List<String> bloodGroups, Boolean status) {
        this.driveId = driveId;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.bloodGroups = bloodGroups;
        this.status = status;
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
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

    public List<String> getBloodGroups() {
        return bloodGroups;
    }

    public void setBloodGroups(List<String> bloodGroups) {
        this.bloodGroups = bloodGroups;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FetchDrivesResponseBody{" +
                "driveId='" + driveId + '\'' +
                ", startTimestamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode='" + pincode + '\'' +
                ", bloodGroups=" + bloodGroups +
                ", status=" + status +
                '}';
    }
}
