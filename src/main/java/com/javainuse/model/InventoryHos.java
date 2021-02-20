package com.javainuse.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@IdClass(InventoryPk.class)
@Table(name = "inventory_hos")
@EntityListeners(AuditingEntityListener.class)
public class InventoryHos {

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

    private int units;

    public InventoryHos() {
        super();
    }

    public InventoryHos(String userId, String component) {
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

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "InventoryHos{" +
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
                ", units=" + units +
                '}';
    }
}