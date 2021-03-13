package com.javainuse.responses;


public class ProfileDataBb_Hos {

    private String name;
    private String userId;
    private String profilePicture;

    public ProfileDataBb_Hos(String name, String userId) {
        this.name = name;
        this.userId = userId;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "ProfileDataBb_Hos{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}