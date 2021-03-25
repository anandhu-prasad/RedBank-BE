package com.javainuse.responses;

import java.util.Date;

public class AuthResponse {
    private String userToken;
    private String userId;
    private int userType;
    private Date authTokenExpiry;
    private String refreshToken;
    private Date refreshTokenExpiry;

    public AuthResponse() {
        super();
    }

    public AuthResponse(String userToken, String userId, int userType, Date authTokenExpiry, String refreshToken, Date refreshTokenExpiry) {
        this.userToken = userToken;
        this.userId = userId;
        this.userType = userType;
        this.authTokenExpiry = authTokenExpiry;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiry = refreshTokenExpiry;
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

    public Date getAuthTokenExpiry() {
        return authTokenExpiry;
    }

    public void setAuthTokenExpiry(Date authTokenExpiry) {
        this.authTokenExpiry = authTokenExpiry;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }

    public void setRefreshTokenExpiry(Date refreshTokenExpiry) {
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "userToken='" + userToken + '\'' +
                ", userId='" + userId + '\'' +
                ", userType=" + userType +
                ", authTokenExpiry='" + authTokenExpiry + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiry='" + refreshTokenExpiry + '\'' +
                '}';
    }
}
