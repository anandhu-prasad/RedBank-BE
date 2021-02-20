package com.javainuse.util;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class DonationInvitedDonorsPk implements Serializable {


    public DonationInvitedDonorsPk() {
    }

    public DonationInvitedDonorsPk(String donationId, String userId) {
        this.donationId = donationId;
        this.userId = userId;
    }

    //    @Column(name="donation_id")
    private String donationId;

//    @Column(name="user_id")
    private String userId;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationInvitedDonorsPk that = (DonationInvitedDonorsPk) o;
        return Objects.equals(donationId, that.donationId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donationId, userId);
    }

    @Override
    public String toString() {
        return "DonationInvitedDonorsId{" +
                "donationId='" + donationId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
