package com.javainuse.responses;


public class ProfileDataInd {

    private String name;
    private String userId;
    private int donorStatus;

    public ProfileDataInd(String name, String userId, int donorStatus) {
        this.name = name;
        this.userId = userId;
        this.donorStatus = donorStatus;
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
}





//List<> list = new ArrayList<>();
//
//list = profileIndRepo.findbyUserid
