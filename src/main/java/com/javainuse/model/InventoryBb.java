package com.javainuse.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@IdClass(InventoryPk.class)
@Table(name = "inventory_bb")
@EntityListeners(AuditingEntityListener.class)
public class InventoryBb {

//    @EmbeddedId
//    private InventoryId inventoryId;

    @Id
    @Column (name = "user_id")
    private String userId;

    @Id
    @Column(name ="component", nullable = false)
    private String component;

    @Column(name = "b_pos_units", columnDefinition = "int default 0")
    private int bPosUnits;

    @Column(name = "b_neg_units", columnDefinition = "int default 0")
    private int bNegUnits;

    @Column(name = "a_pos_units", columnDefinition = "int default 0")
    private int aPosUnits;

    @Column(name = "a_neg_units", columnDefinition = "int default 0")
    private int aNegUnits;

    @Column(name = "o_pos_units", columnDefinition = "int default 0")
    private int oPosUnits;

    @Column(name = "o_neg_units", columnDefinition = "int default 0")
    private int oNegUnits;

    @Column(name = "ab_pos_units", columnDefinition = "int default 0")
    private int abPosUnits;

    @Column(name = "ab_neg_units", columnDefinition = "int default 0")
    private int abNegUnits;

    ////////////////////////////////////

    @Column(name = "b_pos_price", columnDefinition = "double(10,2) default 0.00")
    private double bPosPrice;

    @Column(name = "b_neg_price", columnDefinition = "double(10,2) default 0.00")
    private double bNegPrice;

    @Column(name = "a_pos_price", columnDefinition = "double(10,2) default 0.00")
    private double aPosPrice;

    @Column(name = "a_neg_price", columnDefinition = "double(10,2) default 0.00")
    private double aNegPrice;

    @Column(name = "o_pos_price", columnDefinition = "double(10,2) default 0.00")
    private double oPosPrice;

    @Column(name = "o_neg_price", columnDefinition = "double(10,2) default 0.00")
    private double oNegPrice;

    @Column(name = "ab_pos_price", columnDefinition = "double(10,2) default 0.00")
    private double abPosPrice;

    @Column(name = "ab_neg_price", columnDefinition = "double(10,2) default 0.00")
    private double abNegPrice;

    public InventoryBb() {
        super();
    }

    public InventoryBb(String userId, String component) {
        this.userId = userId;
        this.component = component;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getbPosUnits() {
        return bPosUnits;
    }

    public void setbPosUnits(int bPosUnits) {
        this.bPosUnits = bPosUnits;
    }

    public int getbNegUnits() {
        return bNegUnits;
    }

    public void setbNegUnits(int bNegUnits) {
        this.bNegUnits = bNegUnits;
    }

    public int getaPosUnits() {
        return aPosUnits;
    }

    public void setaPosUnits(int aPosUnits) {
        this.aPosUnits = aPosUnits;
    }

    public int getaNegUnits() {
        return aNegUnits;
    }

    public void setaNegUnits(int aNegUnits) {
        this.aNegUnits = aNegUnits;
    }

    public int getoPosUnits() {
        return oPosUnits;
    }

    public void setoPosUnits(int oPosUnits) {
        this.oPosUnits = oPosUnits;
    }

    public int getoNegUnits() {
        return oNegUnits;
    }

    public void setoNegUnits(int oNegUnits) {
        this.oNegUnits = oNegUnits;
    }

    public int getAbPosUnits() {
        return abPosUnits;
    }

    public void setAbPosUnits(int abPosUnits) {
        this.abPosUnits = abPosUnits;
    }

    public int getAbNegUnits() {
        return abNegUnits;
    }

    public void setAbNegUnits(int abNegUnits) {
        this.abNegUnits = abNegUnits;
    }

    public double getbPosPrice() {
        return bPosPrice;
    }

    public void setbPosPrice(double bPosPrice) {
        this.bPosPrice = bPosPrice;
    }

    public double getbNegPrice() {
        return bNegPrice;
    }

    public void setbNegPrice(double bNegPrice) {
        this.bNegPrice = bNegPrice;
    }

    public double getaPosPrice() {
        return aPosPrice;
    }

    public void setaPosPrice(double aPosPrice) {
        this.aPosPrice = aPosPrice;
    }

    public double getaNegPrice() {
        return aNegPrice;
    }

    public void setaNegPrice(double aNegPrice) {
        this.aNegPrice = aNegPrice;
    }

    public double getoPosPrice() {
        return oPosPrice;
    }

    public void setoPosPrice(double oPosPrice) {
        this.oPosPrice = oPosPrice;
    }

    public double getoNegPrice() {
        return oNegPrice;
    }

    public void setoNegPrice(double oNegPrice) {
        this.oNegPrice = oNegPrice;
    }

    public double getAbPosPrice() {
        return abPosPrice;
    }

    public void setAbPosPrice(double abPosPrice) {
        this.abPosPrice = abPosPrice;
    }

    public double getAbNegPrice() {
        return abNegPrice;
    }

    public void setAbNegPrice(double abNegPrice) {
        this.abNegPrice = abNegPrice;
    }

    @Override
    public String toString() {
        return "InventoryBb{" +
                "userId='" + userId + '\'' +
                ", component='" + component + '\'' +
                ", bPosUnits=" + bPosUnits +
                ", bNegUnits=" + bNegUnits +
                ", aPosUnits=" + aPosUnits +
                ", aNegUnits=" + aNegUnits +
                ", oPosUnits=" + oPosUnits +
                ", oNegUnits=" + oNegUnits +
                ", abPosUnits=" + abPosUnits +
                ", abNegUnits=" + abNegUnits +
                ", bPosPrice=" + bPosPrice +
                ", bNegPrice=" + bNegPrice +
                ", aPosPrice=" + aPosPrice +
                ", aNegPrice=" + aNegPrice +
                ", oPosPrice=" + oPosPrice +
                ", oNegPrice=" + oNegPrice +
                ", abPosPrice=" + abPosPrice +
                ", abNegPrice=" + abNegPrice +
                '}';
    }
}
