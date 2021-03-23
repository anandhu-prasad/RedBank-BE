package com.javainuse.responses;

import java.sql.Timestamp;

public class Purchases_RespBody {
    private String purchaseId; // same as sales_id
    private Timestamp dateOfTransaction;
    private String sellerName;
    private String sellerEmail;
    private String sellerContact;
    private String soldComponent;
    private String soldGroup;
    private int soldQuantity;
    private Double pricePerUnit;
    private String reason;
    private String location;

    public Purchases_RespBody(){
        super();
    }

    public Purchases_RespBody(String purchaseId, Timestamp dateOfTransaction,
                              String sellerName, String sellerEmail,
                              String sellerContact, String soldComponent,
                              String soldGroup, int soldQuantity,
                              Double pricePerUnit, String reason,
                              String location) {
        this.purchaseId = purchaseId;
        this.dateOfTransaction = dateOfTransaction;
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
        this.sellerContact = sellerContact;
        this.soldComponent = soldComponent;
        this.soldGroup = soldGroup;
        this.soldQuantity = soldQuantity;
        this.pricePerUnit = pricePerUnit;
        this.reason = reason;
        this.location = location;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Timestamp getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Timestamp dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerContact() {
        return sellerContact;
    }

    public void setSellerContact(String sellerContact) {
        this.sellerContact = sellerContact;
    }

    public String getSoldComponent() {
        return soldComponent;
    }

    public void setSoldComponent(String soldComponent) {
        this.soldComponent = soldComponent;
    }

    public String getSoldGroup() {
        return soldGroup;
    }

    public void setSoldGroup(String soldGroup) {
        this.soldGroup = soldGroup;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Purchases_RespBody{" +
                "purchaseId='" + purchaseId + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                ", sellerName='" + sellerName + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", sellerContact='" + sellerContact + '\'' +
                ", soldComponent='" + soldComponent + '\'' +
                ", soldGroup='" + soldGroup + '\'' +
                ", soldQuantity=" + soldQuantity +
                ", pricePerUnit=" + pricePerUnit +
                ", reason='" + reason + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
