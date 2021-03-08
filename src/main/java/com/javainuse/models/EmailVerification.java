package com.javainuse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="email_verification")
public class EmailVerification {

    @Id
    @Column(name="user_email")
    private String userEmail;

    @Column(name="otp")
    private int otpReceived;

    public EmailVerification(String userEmail, int otpReceived) {
        this.userEmail = userEmail;
        this.otpReceived = otpReceived;
    }
    public EmailVerification(){
        super();
    }

//    public String getOtpId() {
//        return otpId;
//    }
//
//    public void setOtpId(String otpId) {
//        this.otpId = otpId;
//    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getOtpReceived() {
        return otpReceived;
    }

    public void setOtpReceived(int otpReceived) {
        this.otpReceived = otpReceived;
    }

    @Override
    public String toString() {
        return "EmailOtpMapping{" +
//                "otpId='" + otpId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", otpReceived='" + otpReceived + '\'' +
                '}';
    }

}
