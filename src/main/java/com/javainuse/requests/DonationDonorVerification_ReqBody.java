package com.javainuse.requests;

public class DonationDonorVerification_ReqBody {

    private Boolean status;
    private String donationId;
    private String userid;

    public DonationDonorVerification_ReqBody() {
        super();
    }

    public DonationDonorVerification_ReqBody(Boolean status, String donationId, String userid) {
        this.status = status;
        this.donationId = donationId;
        this.userid = userid;
    }

    public Boolean getstatus() {
        return status;
    }

    public void setstatus(Boolean status) {
        this.status = status;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationid(String donationId) {
        this.donationId = donationId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "DonationDonorVerification_ReqBody{" +
                "status=" + status +
                ", donationId='" + donationId + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
