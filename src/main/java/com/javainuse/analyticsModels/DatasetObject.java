package com.javainuse.analyticsModels;

import java.util.List;

public class DatasetObject {
    private List<Integer> data;
    //private final String color = "(opacity = 1) => `rgba(134, 65, 244, ${opacity})`";
    //private final Integer strokeWidth = 1;

    public DatasetObject() {
    }

    public DatasetObject(List<Integer> data) {
        this.data = data;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DatasetObject{" +
                "data=" + data +
                '}';
    }
}
