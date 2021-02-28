package com.javainuse.requests;

public class ResetPassword_ReqBody {

    private String userEmail;
    private String newPassword;

    public ResetPassword_ReqBody(){
        super();
    }

    public ResetPassword_ReqBody(String userEmail, String newPassword) {
        this.userEmail = userEmail;
        this.newPassword = newPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ResetPassword_ReqBody{" +
                "userEmail='" + userEmail + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
