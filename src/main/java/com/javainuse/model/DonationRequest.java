package com.javainuse.model;

import com.javainuse.util.StringSequenceIdentifier;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "donation_requests")
@EntityListeners(AuditingEntityListener.class)
public class DonationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drive_seq")
    @GenericGenerator(name = "drive_seq", strategy = "com.javainuse.util.StringSequenceIdentifier", parameters = {
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.VALUE_PREFIX_PARAMETER, value = "DON"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    @Column(name = "donation_id")
    private String donationId;

    @Column(name = "user_id")
    private String userId;

    private String address;
    private String state;
    private String district;
    private int pincode;

    @Column(name = "request_time")
    private Timestamp requestTime;

    private boolean status;

    @Column(name = "blood_group")
    private String bloodGroup;

    public DonationRequest() {
        super();
    }

    public DonationRequest(String userId, String address, String state, String district, int pincode, Timestamp requestTime, boolean status, String bloodGroup) {
        this.userId = userId;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.requestTime = requestTime;
        this.status = status;
        this.bloodGroup = bloodGroup;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "DonationRequest{" +
                "donationId='" + donationId + '\'' +
                ", userId='" + userId + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode=" + pincode +
                ", requestTime=" + requestTime +
                ", status=" + status +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
