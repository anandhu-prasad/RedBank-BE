package com.javainuse.responses;

import java.sql.Timestamp;

public class Sales_RespBody {

    private String salesId;
    private Timestamp dateOfTransaction;
    private String buyerName;
    private String buyerEmail;
    private String buyerContact;
    private String purchasedComponent;
    private String purchasedGroup;
    private int purchasedQuantity;
    private Double pricePerUnit;

    public Sales_RespBody() {
        super();
    }

    public Sales_RespBody(String salesId, Timestamp dateOfTransaction,
                          String buyerName, String buyerEmail, String buyerContact,
                          String purchasedComponent, String purchasedGroup,
                          int purchasedQuantity, Double pricePerUnit) {
        this.salesId = salesId;
        this.dateOfTransaction = dateOfTransaction;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerContact = buyerContact;
        this.purchasedComponent = purchasedComponent;
        this.purchasedGroup = purchasedGroup;
        this.purchasedQuantity = purchasedQuantity;
        this.pricePerUnit = pricePerUnit;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public Timestamp getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Timestamp dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerContact() {
        return buyerContact;
    }

    public void setBuyerContact(String buyerContact) {
        this.buyerContact = buyerContact;
    }

    public String getPurchasedComponent() {
        return purchasedComponent;
    }

    public void setPurchasedComponent(String purchasedComponent) {
        this.purchasedComponent = purchasedComponent;
    }

    public String getPurchasedGroup() {
        return purchasedGroup;
    }

    public void setPurchasedGroup(String purchasedGroup) {
        this.purchasedGroup = purchasedGroup;
    }

    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void setPurchasedQuantity(int purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return "Sales_RespBody{" +
                "salesId='" + salesId + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                ", buyerName='" + buyerName + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                ", buyerContact='" + buyerContact + '\'' +
                ", purchasedComponent='" + purchasedComponent + '\'' +
                ", purchasedGroup='" + purchasedGroup + '\'' +
                ", purchasedQuantity='" + purchasedQuantity + '\'' +
                ", pricePerUnit='" + pricePerUnit + '\'' +
                '}';
    }
}
