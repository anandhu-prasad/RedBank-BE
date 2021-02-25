package com.javainuse.requests;


public class DonationDonorVerification_ReqBody {

    private String donationId;
    private String userId;

    public DonationDonorVerification_ReqBody() {
        super();
    }

    public DonationDonorVerification_ReqBody(String donationId, String userId) {
        this.donationId = donationId;
        this.userId = userId;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DonationDonorVerification_ReqBody{" +
                "donationId='" + donationId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}