package com.javainuse.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(DriveInvitedDonorsPk.class)
@Table(name = "drive_invited_donors")
@EntityListeners(AuditingEntityListener.class)
public class DriveInvitedDonors {


//    @EmbeddedId
//    private DriveInvitedDonorsId driveInvitedDonorsId;

    @Id
    @Column(name="drive_id")
    private String driveId;

    @Id
    @Column(name="user_id")
    private String userId;

    private boolean donation_status;

    @Column( name="response_timestamp")
    private Timestamp responseTimeStamp;

    @Column( name="donation_timestamp")
    private Timestamp donationTimestamp;

    private int acceptance;

    private String rejection_message;

    public DriveInvitedDonors() {
        super();
    }

    public DriveInvitedDonors(String driveId, String userId, boolean donation_status, Timestamp responseTimeStamp, Timestamp donationTimestamp, int acceptance, String rejection_message) {
        this.driveId = driveId;
        this.userId = userId;
        this.donation_status = donation_status;
        this.responseTimeStamp = responseTimeStamp;
        this.donationTimestamp = donationTimestamp;
        this.acceptance = acceptance;
        this.rejection_message = rejection_message;
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

    public boolean isDonation_status() {
        return donation_status;
    }

    public void setDonation_status(boolean donation_status) {
        this.donation_status = donation_status;
    }

    public int getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(int acceptance) {
        this.acceptance = acceptance;
    }

    public String getRejection_message() {
        return rejection_message;
    }

    public void setRejection_message(String rejection_message) {
        this.rejection_message = rejection_message;
    }

    public Timestamp getResponseTimeStamp() {
        return responseTimeStamp;
    }

    public void setResponseTimeStamp(Timestamp responseTimeStamp) {
        this.responseTimeStamp = responseTimeStamp;
    }

    public Timestamp getDonationTimestamp() {
        return donationTimestamp;
    }

    public void setDonationTimestamp(Timestamp donationTimestamp) {
        this.donationTimestamp = donationTimestamp;
    }
}
