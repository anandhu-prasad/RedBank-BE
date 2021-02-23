package com.javainuse.requests;

public class DonorStatusRequestBody {

    private int donorStatus;

    public DonorStatusRequestBody() {
        super();
    }

    public DonorStatusRequestBody(int donorStatus) {
        this.donorStatus = donorStatus;
    }

    public int getDonorStatus() {
        return donorStatus;
    }

    public void setDonorStatus(int donorStatus) {
        this.donorStatus = donorStatus;
    }

    @Override
    public String toString() {
        return "DonorStatusRequestBody{" +
                "donorStatus=" + donorStatus +
                '}';
    }
}
