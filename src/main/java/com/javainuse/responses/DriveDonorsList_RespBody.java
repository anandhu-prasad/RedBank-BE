package com.javainuse.responses;

public class DriveDonorsList_RespBody {
    private String userId;
    private String name;
    private String bloodGroup;
    private Boolean donationStatus;
    private int acceptance;

    public DriveDonorsList_RespBody() {
        super();
    }

    public DriveDonorsList_RespBody(String userId, String name, String bloodGroup, Boolean donationStatus, int acceptance) {
        this.userId = userId;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.donationStatus = donationStatus;
        this.acceptance = acceptance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Boolean getDonationStatus() {
        return donationStatus;
    }

    public void setDonationStatus(Boolean donationStatus) {
        this.donationStatus = donationStatus;
    }

    public int getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(int acceptance) {
        this.acceptance = acceptance;
    }

    @Override
    public String toString() {
        return "DriveDonorsList_RespBody{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", donationStatus=" + donationStatus +
                ", acceptance=" + acceptance +
                '}';
    }
}
