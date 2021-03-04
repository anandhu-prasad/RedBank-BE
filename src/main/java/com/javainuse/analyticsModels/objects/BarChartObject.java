package com.javainuse.analyticsModels.objects;

import java.util.List;

public class BarChartObject {
    private List<Double> data;

    public BarChartObject() {
        super();
    }

    public BarChartObject(List<Double> data) {
        this.data = data;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BarChartObject{" +
                "data=" + data +
                '}';
    }
}
