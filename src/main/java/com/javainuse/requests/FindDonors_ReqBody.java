package com.javainuse.requests;


public class FindDonors_ReqBody {

    private String address;
    private String state;
    private String district;
    private String pincode;
    private String bloodGroup;

    public FindDonors_ReqBody(){
        super();
    }
    public FindDonors_ReqBody(String address, String state, String district, String pincode, String bloodGroup) {
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.bloodGroup = bloodGroup;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "FindDonors{" +
                "address='" + address + '\'' +
                "state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", pincode=" + pincode +
                ", bloodGroup=" + bloodGroup +
                '}';
    }
}
