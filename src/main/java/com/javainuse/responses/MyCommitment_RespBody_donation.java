package com.javainuse.responses;


import java.sql.Timestamp;

public class MyCommitment_RespBody_donation {
    private Timestamp commitment_timeStamp;
    private String commitmentType;
    private String recipientType;
    private String recipientId;
    private Integer recipientContact;
    private String recipientAddress;
    private Boolean status;    // commitment status -> Completed/incomplete



    public MyCommitment_RespBody_donation(Timestamp commitment_timeStamp, String commitmentType, String recipientType, String recipientId, Integer recipientContact, String recipientAddress, Boolean status) {
        this.commitment_timeStamp = commitment_timeStamp;
        this.commitmentType = commitmentType;
        this.recipientType = recipientType;
        this.recipientId = recipientId;
        this.recipientContact = recipientContact;
        this.recipientAddress = recipientAddress;
        this.status = status;
    }

    public MyCommitment_RespBody_donation(){
        super();
    }

    public Timestamp getDateTime() {
        return commitment_timeStamp;
    }

    public void setDateTime(Timestamp commitment_timeStamp) {
        this.commitment_timeStamp = commitment_timeStamp;
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

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public Integer getRecipientContact() {
        return recipientContact;
    }

    public void setRecipientContact(Integer recipientContact) {
        this.recipientContact = recipientContact;
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
        return "MyCommintment_RespBody_drive{" +
                "commitment_timeStamp=" + commitment_timeStamp +
                ", commitmentType='" + commitmentType + '\'' +
                ", recipientType='" + recipientType + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", recipientContact=" + recipientContact +
                ", recipientAddress='" + recipientAddress + '\'' +
                ", status=" + status +
                '}';
    }
}
