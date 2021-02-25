package com.javainuse.responses;

import java.sql.Timestamp;

public class DonorStatusNotificationResponseBody {
    private Timestamp lastDonationDate;

    public DonorStatusNotificationResponseBody() {
        super();
    }

    public DonorStatusNotificationResponseBody(Timestamp lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public Timestamp getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(Timestamp lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    @Override
    public String toString() {
        return "lastDonationDateResponseBody{" +
                "lastDonationDate=" + lastDonationDate +
                '}';
    }
}
