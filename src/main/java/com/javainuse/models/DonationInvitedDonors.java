package com.javainuse.models;


import com.javainuse.util.DonationInvitedDonorsPk;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(DonationInvitedDonorsPk.class)
@Table(name = "donation_invited_donors")
@EntityListeners(AuditingEntityListener.class)
public class DonationInvitedDonors {
    @Id
    @Column(name="donation_id")
    private String donationId;

    @Id
    @Column(name="user_id")
    private String userId;

    @Column( name="response_timestamp")
    private Timestamp responseTimeStamp;

    @Column( name="donation_timestamp")
    private Timestamp donationTimestamp;

    @Column( name="donation_status", columnDefinition = "boolean default false")
    private boolean donation_status;

    @Column( name="acceptance", columnDefinition = "int default 2")
    private int acceptance;

    @Column( name="rejection_message")
    private String rejection_message;


    public DonationInvitedDonors() {
        super();
    }

    public DonationInvitedDonors(String donationId, String userId) {
        this.donationId = donationId;
        this.userId = userId;
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
        return donationTimestamp;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationTimestamp = donationDate;
    }

    @Override
    public String toString() {
        return "DonationInvitedDonors{" +
                "donationId='" + donationId + '\'' +
                ", userId='" + userId + '\'' +
                ", responseTimeStamp=" + responseTimeStamp +
                ", donationTimestamp=" + donationTimestamp +
                ", donation_status=" + donation_status +
                ", acceptance=" + acceptance +
                ", rejection_message='" + rejection_message + '\'' +
                '}';
    }
}
