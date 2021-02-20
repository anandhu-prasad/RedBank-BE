package com.javainuse.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "drives")
@EntityListeners(AuditingEntityListener.class)
public class Drives {
    @Id
    @Column(name = "drive_id")
    private String driveId;

    @Column(name = "user_id")
    private String userId;

    private String address;
    private String state;
    private String district;
    private int pincode;

    @Column(name = "request_time")
    private Timestamp requestTime;

    private Timestamp start_timestamp;

    private Timestamp end_timestamp;

    private boolean status;

    @Column(name = "b_pos")
    private boolean bPos;

    @Column(name = "b_neg")
    private boolean bNeg;

    @Column(name = "a_pos")
    private boolean aPos;

    @Column(name = "a_neg")
    private boolean aNeg;

    @Column(name = "o_pos")
    private boolean oPos;

    @Column(name = "o_neg")
    private boolean oNeg;

    @Column(name = "ab_pos")
    private boolean abPos;

    @Column(name = "ab_neg")
    private boolean abNeg;

    public Drives() {
        super();
    }

    public Drives(String driveId, String userId, String address, String state, String district, int pincode, Timestamp requestTime, Timestamp start_timestamp, Timestamp end_timestamp, boolean status, boolean bPos, boolean bNeg, boolean aPos, boolean aNeg, boolean oPos, boolean oNeg, boolean abPos, boolean abNeg) {
        this.driveId = driveId;
        this.userId = userId;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.requestTime = requestTime;
        this.start_timestamp = start_timestamp;
        this.end_timestamp = end_timestamp;
        this.status = status;
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

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isbPos() {
        return bPos;
    }

    public void setbPos(boolean bPos) {
        this.bPos = bPos;
    }

    public boolean isbNeg() {
        return bNeg;
    }

    public void setbNeg(boolean bNeg) {
        this.bNeg = bNeg;
    }

    public boolean isaPos() {
        return aPos;
    }

    public void setaPos(boolean aPos) {
        this.aPos = aPos;
    }

    public boolean isaNeg() {
        return aNeg;
    }

    public void setaNeg(boolean aNeg) {
        this.aNeg = aNeg;
    }

    public boolean isoPos() {
        return oPos;
    }

    public void setoPos(boolean oPos) {
        this.oPos = oPos;
    }

    public boolean isoNeg() {
        return oNeg;
    }

    public void setoNeg(boolean oNeg) {
        this.oNeg = oNeg;
    }

    public boolean isAbPos() {
        return abPos;
    }

    public void setAbPos(boolean abPos) {
        this.abPos = abPos;
    }

    public boolean isAbNeg() {
        return abNeg;
    }

    public void setAbNeg(boolean abNeg) {
        this.abNeg = abNeg;
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

    @Override
    public String toString() {
        return "Drives{" +
                "driveId='" + driveId + '\'' +
                ", userId='" + userId + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode=" + pincode +
                ", requestTime=" + requestTime +
                ", start_timestamp=" + start_timestamp +
                ", end_timestamp=" + end_timestamp +
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
