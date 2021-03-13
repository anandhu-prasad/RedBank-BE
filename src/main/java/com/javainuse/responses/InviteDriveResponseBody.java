package com.javainuse.responses;

import java.sql.Timestamp;

public class InviteDriveResponseBody {
    private Timestamp inviteTimestamp;
    private final String inviteType = "drive";
    private String driveId;
    private int status;
    private String recipientName;
    private String recipientType;
    private String recipientEmail;
    private String recipientContact;
    private String address;
    private String district;
    private String state;
    private String pincode;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private String message;
    private String avatar;

    public InviteDriveResponseBody() {
        super();
    }

    public InviteDriveResponseBody(Timestamp inviteTimestamp, String driveId, int status, String recipientName, String recipientType, String recipientEmail, String recipientContact, String address, String district, String state, String pincode, Timestamp startTimestamp, Timestamp endTimestamp, String message, String avatar) {
        this.inviteTimestamp = inviteTimestamp;
        this.driveId = driveId;
        this.status = status;
        this.recipientName = recipientName;
        this.recipientType = recipientType;
        this.recipientEmail = recipientEmail;
        this.recipientContact = recipientContact;
        this.address = address;
        this.district = district;
        this.state = state;
        this.pincode = pincode;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.message = message;
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

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "InviteDriveResponseBody{" +
                "inviteTimestamp=" + inviteTimestamp +
                ", inviteType='" + inviteType + '\'' +
                ", driveId='" + driveId + '\'' +
                ", status=" + status +
                ", recipientName='" + recipientName + '\'' +
                ", recipientType='" + recipientType + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", recipientContact='" + recipientContact + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", startTimestamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                ", message='" + message + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
