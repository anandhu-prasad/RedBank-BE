package com.javainuse.requests;

public class DriveDonorVerification_ReqBody {

    private String driveId;
    private String userId;

    public DriveDonorVerification_ReqBody() {
        super();
    }

    public DriveDonorVerification_ReqBody(String driveId, String userId) {
        this.driveId = driveId;
        this.userId = userId;
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
                "driveId='" + driveId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
