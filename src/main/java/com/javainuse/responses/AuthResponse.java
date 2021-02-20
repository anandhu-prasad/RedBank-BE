package com.javainuse.responses;

public class AuthResponse {
    private String userToken;
    private String userId;
    private int userType;

    public AuthResponse() {
    }

    public AuthResponse(String userToken, String userId, int userType) {
        this.userToken = userToken;
        this.userId = userId;
        this.userType = userType;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "userToken='" + userToken + '\'' +
                ", userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
