package com.javainuse.responses;

import java.sql.Timestamp;

public class MyCommitment_RespBody_drive {
    private Timestamp dateTime;
    private String commitmentType;
    private String recipientType;
    private String recipientName;
    private String recipientContact;
    private String recipientEmail;
    private String recipientAddress;
    private Boolean status; // commitment status -> Completed/incomplete
    private Timestamp startTimeStamp;
    private Timestamp endTimeStamp;

    public MyCommitment_RespBody_drive(Timestamp dateTime, String commitmentType, String recipientType,
            String recipientName, String recipientContact, String recipientEmail, String recipientAddress,
            Boolean status, Timestamp startTimeStamp, Timestamp endTimeStamp) {
        this.dateTime = dateTime;
        this.commitmentType = commitmentType;
        this.recipientType = recipientType;
        this.recipientName = recipientName;
        this.recipientContact = recipientContact;
        this.recipientEmail = recipientEmail;
        this.recipientAddress = recipientAddress;
        this.status = status;
        this.startTimeStamp = startTimeStamp;
        this.endTimeStamp = endTimeStamp;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
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

    public Timestamp getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Timestamp startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public Timestamp getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(Timestamp endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    @Override
    public String toString() {
        return "MyCommitment_RespBody_drive{" + "dateTime=" + dateTime + ", commitmentType='" + commitmentType + '\''
                + ", recipientType='" + recipientType + '\'' + ", recipientName='" + recipientName + '\''
                + ", recipientContact='" + recipientContact + '\'' + ", recipientEmail='" + recipientEmail + '\''
                + ", recipientAddress='" + recipientAddress + '\'' + ", status=" + status + ", startTimeStamp="
                + startTimeStamp + ", endTimeStamp=" + endTimeStamp + '}';
    }
}
