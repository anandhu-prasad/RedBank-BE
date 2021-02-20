package com.javainuse.models;

import com.javainuse.util.StringSequenceIdentifier;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "drives")
@EntityListeners(AuditingEntityListener.class)
public class Drives {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drive_seq")
    @GenericGenerator(
            name = "drive_seq",
            strategy = "com.javainuse.util.StringSequenceIdentifier",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.VALUE_PREFIX_PARAMETER, value = "DRV"),
                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    @Column(name = "drive_id")
    private String driveId;

    @Column(name = "user_id")
    private String userId;

    private String address;
    private String state;
    private String district;
    private int pincode;
    private String message;

    @Column(name = "request_time")
    private Timestamp requestTime;

    @Column(name = "start_timestamp")
    private Timestamp startTimestamp;

    @Column(name = "end_timestamp")
    private Timestamp endTimestamp;

    @Column(name="status")
    private Boolean status;

    @Column(name = "b_pos")
    private Boolean bPos;

    @Column(name = "b_neg")
    private Boolean bNeg;

    @Column(name = "a_pos")
    private Boolean aPos;

    @Column(name = "a_neg")
    private Boolean aNeg;

    @Column(name = "o_pos")
    private Boolean oPos;

    @Column(name = "o_neg")
    private Boolean oNeg;

    @Column(name = "ab_pos")
    private Boolean abPos;

    @Column(name = "ab_neg")
    private Boolean abNeg;

    public Drives() {
        super();
    }

    public Drives(String userId, String address, String state, String district, int pincode, String message, Timestamp requestTime, Timestamp startTimestamp, Timestamp endTimestamp, Boolean bPos, Boolean bNeg, Boolean aPos, Boolean aNeg, Boolean oPos, Boolean oNeg, Boolean abPos, Boolean abNeg) {
        this.userId = userId;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.message = message;
        this.requestTime = requestTime;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.bPos = bPos;
        this.bNeg = bNeg;
        this.aPos = aPos;
        this.aNeg = aNeg;
        this.oPos = oPos;
        this.oNeg = oNeg;
        this.abPos = abPos;
        this.abNeg = abNeg;
    }


    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Drives{" +
                "driveId='" + driveId + '\'' +
                ", userId='" + userId + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode=" + pincode +
                ", message='" + message + '\'' +
                ", requestTime=" + requestTime +
                ", startTimestamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                ", status=" + status +
                ", bPos=" + bPos +
                ", bNeg=" + bNeg +
                ", aPos=" + aPos +
                ", aNeg=" + aNeg +
                ", oPos=" + oPos +
                ", oNeg=" + oNeg +
                ", abPos=" + abPos +
                ", abNeg=" + abNeg +
                '}';
    }
}
