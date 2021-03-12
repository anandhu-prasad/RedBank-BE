package com.javainuse.responses;


import java.sql.Timestamp;

public class ProfileDataInd {

    private String name;
    private String userId;
    private int donorStatus;
    private Timestamp lastDonationDate;
    private String profilePicture;

    public ProfileDataInd() {
        super();
    }

    public ProfileDataInd(String name, String userId, int donorStatus, Timestamp lastDonationDate) {
        this.name = name;
        this.userId = userId;
        this.donorStatus = donorStatus;
        this.lastDonationDate = lastDonationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDonorStatus() {
        return donorStatus;
    }

    public void setDonorStatus(int donorStatus) {
        this.donorStatus = donorStatus;
    }

    public Timestamp getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(Timestamp lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "ProfileDataInd{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", donorStatus=" + donorStatus +
                ", lastDonationDate=" + lastDonationDate +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}





//List<> list = new ArrayList<>();
//
//list = profileIndRepo.findbyUserid
