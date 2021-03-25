package com.javainuse.requests;

import java.util.Date;

public class RefreshAuthTokenRespBody {
    private String newAuthToken;
    private Date newAuthTokenExpiry;

    public RefreshAuthTokenRespBody() {
        super();
    }

    public RefreshAuthTokenRespBody(String newAuthToken, Date newAuthTokenExpiry) {
        this.newAuthToken = newAuthToken;
        this.newAuthTokenExpiry = newAuthTokenExpiry;
    }

    public RefreshAuthTokenRespBody(String newAuthToken) {
        this.newAuthToken = newAuthToken;
    }

    public String getNewAuthToken() {
        return newAuthToken;
    }

    public void setNewAuthToken(String newAuthToken) {
        this.newAuthToken = newAuthToken;
    }

    public Date getNewAuthTokenExpiry() {
        return newAuthTokenExpiry;
    }

    public void setNewAuthTokenExpiry(Date newAuthTokenExpiry) {
        this.newAuthTokenExpiry = newAuthTokenExpiry;
    }

    @Override
    public String toString() {
        return "RefreshAuthTokenRespBody{" +
                "newAuthToken='" + newAuthToken + '\'' +
                ", newAuthTokenExpiry=" + newAuthTokenExpiry +
                '}';
    }
}
