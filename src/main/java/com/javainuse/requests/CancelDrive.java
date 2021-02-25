package com.javainuse.requests;

public class CancelDrive {

    private String driveId;

    public CancelDrive() {
        super();
    }

    public CancelDrive(String driveId) {
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
        return "CancelDrive{" +
                "driveId='" + driveId + '\'' +
                '}';
    }
}
