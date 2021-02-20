package com.javainuse.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(DonationInvitedDonorsPk.class)
@Table(name = "donation_invited_donors")
@EntityListeners(AuditingEntityListener.class)
public class DonationInvitedDonors {


//    @EmbeddedId
//    private DonationInvitedDonorsId donationInvitedDonorsId;


    @Id
    @Column(name="donation_id")
    private String donationId;

    @Id
    @Column(name="user_id")
    private String userId;

    @Column( name="response_timestamp")
    private Timestamp responseTimeStamp;

    @Column( name="donation_date")
    private Timestamp donationDate;


    private boolean donation_status;

    private int acceptance;

    private String rejection_message;


    public DonationInvitedDonors() {
        super();
    }

    public DonationInvitedDonors(String donationId, String userId, Timestamp responseTimeStamp, Timestamp donationDate, boolean donation_status, int acceptance, String rejection_message) {
        this.donationId = donationId;
        this.userId = userId;
        this.responseTimeStamp = responseTimeStamp;
        this.donationDate = donationDate;
        this.donation_status = donation_status;
        this.acceptance = acceptance;
        this.rejection_message = rejection_message;
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

    public Timestamp getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationDate = donationDate;
    }
}
