package com.javainuse.responses;

import java.sql.Timestamp;

public class InviteDonationResponseBody {
    private Timestamp inviteTimestamp;
    private final String inviteType = "donation";
    private String donationId;
    private int status;
    private String recipientName;
    private String recipientType;
    private String recipientEmail;
    private String recipientContact;
    private String address;
    private String avatar;

    public InviteDonationResponseBody() {
        super();
    }

    public InviteDonationResponseBody(Timestamp inviteTimestamp, String donationId, int status, String recipientName, String recipientType, String recipientEmail, String recipientContact, String address, String avatar) {
        this.inviteTimestamp = inviteTimestamp;
        this.donationId = donationId;
        this.status = status;
        this.recipientName = recipientName;
        this.recipientType = recipientType;
        this.recipientEmail = recipientEmail;
        this.recipientContact = recipientContact;
        this.address = address;
        this.avatar = avatar;
    }

    public Timestamp getInviteTimestamp() {
        return inviteTimestamp;
    }

    public void setInviteTimestamp(Timestamp inviteTimestamp) {
        this.inviteTimestamp = inviteTimestamp;
    }

    public String getInviteType() {
        return inviteType;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientContact() {
        return recipientContact;
    }

    public void setRecipientContact(String recipientContact) {
        this.recipientContact = recipientContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "InviteDonationResponseBody{" +
                "inviteTimestamp=" + inviteTimestamp +
                ", inviteType='" + inviteType + '\'' +
                ", donationId='" + donationId + '\'' +
                ", status=" + status +
                ", recipientName='" + recipientName + '\'' +
                ", recipientType='" + recipientType + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", recipientContact='" + recipientContact + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
