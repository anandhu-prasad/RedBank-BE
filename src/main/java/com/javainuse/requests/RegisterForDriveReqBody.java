package com.javainuse.requests;

public class RegisterForDriveReqBody {

    private String driveId;

    public RegisterForDriveReqBody() {
        super();
    }

    public RegisterForDriveReqBody(String driveId) {
        this.driveId = driveId;
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    @Override
    public String toString() {
        return "RegisterForDriveReqBody{" +
                "driveId='" + driveId + '\'' +
                '}';
    }
}
