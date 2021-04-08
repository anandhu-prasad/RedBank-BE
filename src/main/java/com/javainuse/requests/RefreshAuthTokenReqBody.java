package com.javainuse.requests;

public class RefreshAuthTokenReqBody {
    private String expiredAuthToken;
    private String refreshToken;

    public RefreshAuthTokenReqBody() {
        super();
    }

    public RefreshAuthTokenReqBody(String expiredAuthToken, String refreshToken) {
        this.expiredAuthToken = expiredAuthToken;
        this.refreshToken = refreshToken;
    }

    public String getExpiredAuthToken() {
        return expiredAuthToken;
    }

    public void setExpiredAuthToken(String expiredAuthToken) {
        this.expiredAuthToken = expiredAuthToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshAuthToken{" +
                "expiredAuthToken='" + expiredAuthToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}

