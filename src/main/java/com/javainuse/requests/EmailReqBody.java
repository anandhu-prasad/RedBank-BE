package com.javainuse.requests;

public class EmailReqBody {
    private String userEmail;

    public EmailReqBody() {
        super();
    }

    public EmailReqBody(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "EmailReqBody{" +
                "userEmail='" + userEmail + '\'' +
                '}';
    }
}
