package com.javainuse.analyticsModels;

import java.util.List;

public class StackedObject {
    public List<Integer> bloodGroupItem;

    public StackedObject() {
        super();
    }

    public StackedObject(List<Integer> bloodGroupItem) {
        this.bloodGroupItem = bloodGroupItem;
    }

    public List<Integer> getBloodGroupItem() {
        return bloodGroupItem;
    }

    public void setBloodGroupItem(List<Integer> bloodGroupItem) {
        this.bloodGroupItem = bloodGroupItem;
    }

    @Override
    public String toString() {
        return "StackedObject{" +
                "bloodGroupItem=" + bloodGroupItem +
                '}';
    }
}
