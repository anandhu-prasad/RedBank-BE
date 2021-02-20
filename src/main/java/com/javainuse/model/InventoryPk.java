package com.javainuse.model;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class InventoryPk implements Serializable {
    //TODO CHANGE THIS TO STRING.
//    @Column (name = "user_id")
    private String userId;

//    @Column(name ="component    ", nullable = false)
    private String component;

//    @Column(name ="blood_group", nullable = false)
//    private String bloodGroup;

    public InventoryPk() {
        super();
    }

    public InventoryPk(String userId, String component, String bloodGroup) {
        this.userId = userId;
        this.component = component;
//        this.bloodGroup = bloodGroup;
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

//    public String getBloodGroup() {
//        return bloodGroup;
//    }
//
//    public void setBloodGroup(String bloodGroup) {
//        this.bloodGroup = bloodGroup;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryPk that = (InventoryPk) o;
        return Objects.equals(userId, that.userId) && Objects.equals(component, that.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, component);
    }

    @Override
    public String toString() {
        return "InventoryId{" +
                "userId='" + userId + '\'' +
                ", component='" + component + '\'' +
//                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
