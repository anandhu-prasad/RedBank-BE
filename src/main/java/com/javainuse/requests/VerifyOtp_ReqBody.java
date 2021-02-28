package com.javainuse.requests;

public class VerifyOtp_ReqBody {
    private String userEmail;
    private int otp;

    public VerifyOtp_ReqBody(){
        super();
    }

    public VerifyOtp_ReqBody(String userEmail, int otp) {
        this.userEmail = userEmail;
        this.otp = otp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "VerifyOtp_ReqBody{" +
                "userEmail='" + userEmail + '\'' +
                ", otp=" + otp +
                '}';
    }
}
