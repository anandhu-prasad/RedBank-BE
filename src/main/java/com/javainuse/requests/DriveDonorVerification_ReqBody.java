package com.javainuse.requests;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class DriveDonorVerification_ReqBody {
    private Boolean status;
    private String driveId;
    private String userId;

    public DriveDonorVerification_ReqBody() {
        super();
    }

    public DriveDonorVerification_ReqBody(Boolean status, String driveId, String userId) {
        this.status = status;
        this.driveId = driveId;
        this.userId = userId;
    }

    public Boolean getstatus() {
        return status;
    }

    public void setstatus(Boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "DriveDonorVerification_ReqBody{" +
                "status=" + status +
                ", driveId='" + driveId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
