package com.javainuse.util;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class DriveInvitedDonorsPk implements Serializable {
//    @Column(name="drive_id")
    private String driveId;

//    @Column(name="user_id")
    private String userId;


    public DriveInvitedDonorsPk() {
    }

    public DriveInvitedDonorsPk(String driveId, String userId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriveInvitedDonorsPk that = (DriveInvitedDonorsPk) o;
        return Objects.equals(driveId, that.driveId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driveId, userId);
    }

    @Override
    public String toString() {
        return "DriveInvitedDonorsId{" +
                "driveId='" + driveId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
