package com.javainuse.models;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_seq")
    @GenericGenerator(
            name = "donation_seq",
            strategy = "com.javainuse.util.StringSequenceIdentifier",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.VALUE_PREFIX_PARAMETER, value = "DON"),
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    @Column(name="donation_id")
    private String donationId;

    @Column(name="user_id")
    private String userId;

    @Column(name="request_time")
    private Timestamp requestTime;

    private String address;
    private String state;
    private String district;
    private Integer pincode;

    @Column(name="blood_group")
    private String bloodGroup;
    private Boolean status;

    public DonationRequest(String userId, Timestamp requestTime, String address, String state, String district, Integer pincode, String bloodGroup, Boolean status) {
        this.userId = userId;
        this.requestTime = requestTime;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.bloodGroup = bloodGroup;
        this.status = status;
    }

    public DonationRequest(){
        super();
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

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
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

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DonorRequest{" +
                "donationId=" + donationId +
                ", userId='" + userId + '\'' +
                ", requestTime=" + requestTime +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode=" + pincode +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", status=" + status +
                '}';
    }
}
