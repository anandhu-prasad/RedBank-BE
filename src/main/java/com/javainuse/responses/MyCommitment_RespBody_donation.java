package com.javainuse.responses;

import java.sql.Timestamp;

public class MyCommitment_RespBody_donation {
    private Timestamp commitment_timeStamp;
    private String commitmentType;
    private String recipientType;
    private String recipientName;
    private String recipientContact;
    private String recipientEmail;
    private String recipientAddress;
    private Boolean status; // commitment status -> Completed/incomplete

    public MyCommitment_RespBody_donation(Timestamp commitment_timeStamp, String commitmentType, String recipientType,
            String recipientName, String recipientContact, String recipientEmail, String recipientAddress,
            Boolean status) {
        this.commitment_timeStamp = commitment_timeStamp;
        this.commitmentType = commitmentType;
        this.recipientType = recipientType;
        this.recipientName = recipientName;
        this.recipientContact = recipientContact;
        this.recipientEmail = recipientEmail;
        this.recipientAddress = recipientAddress;
        this.status = status;
    }

    public MyCommitment_RespBody_donation() {
        super();
    }

    public String getCommitmentType() {
        return commitmentType;
    }

    public void setCommitmentType(String commitmentType) {
        this.commitmentType = commitmentType;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Timestamp getCommitment_timeStamp() {
        return commitment_timeStamp;
    }

    public void setCommitment_timeStamp(Timestamp commitment_timeStamp) {
        this.commitment_timeStamp = commitment_timeStamp;
    }

    public String getRecipientContact() {
        return recipientContact;
    }

    public void setRecipientContact(String recipientContact) {
        this.recipientContact = recipientContact;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyCommitment_RespBody_donation{" + "commitment_timeStamp=" + commitment_timeStamp + ", commitmentType='"
                + commitmentType + '\'' + ", recipientType='" + recipientType + '\'' + ", recipientName='"
                + recipientName + '\'' + ", recipientContact='" + recipientContact + '\'' + ", recipientEmail='"
                + recipientEmail + '\'' + ", recipientAddress='" + recipientAddress + '\'' + ", status=" + status + '}';
    }
}
