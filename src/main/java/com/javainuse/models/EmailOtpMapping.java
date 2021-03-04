package com.javainuse.models;

import org.hibernate.annotations.GenericGenerator;
import com.javainuse.util.StringSequenceIdentifier;

import javax.persistence.*;

@Entity
@Table(name="otp")
public class EmailOtpMapping {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_seq")
//    @GenericGenerator(
//            name = "otp_seq",
//            strategy = "com.javainuse.util.StringSequenceIdentifier",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.INCREMENT_PARAM, value = "1"),
//                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.VALUE_PREFIX_PARAMETER, value = "OTP"),
//                    @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.NUMBER_FORMAT_PARAMETER, value = "%02d") })
//    @Column(name="otp_id")
//    private String otpId;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="otp")
    private int otpReceived;

    public EmailOtpMapping(String userEmail, int otpReceived) {
        this.userEmail = userEmail;
        this.otpReceived = otpReceived;
    }
    public EmailOtpMapping(){
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
