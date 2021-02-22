package com.javainuse.responses;


public class ProfileDataBb_Hos {

    private String name;
    private String userId;

    public ProfileDataBb_Hos(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public ProfileDataBb_Hos() {
        super();
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

}
