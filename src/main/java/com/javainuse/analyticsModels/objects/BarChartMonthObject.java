package com.javainuse.analyticsModels.objects;

import java.util.List;

public class BarChartMonthObject {
    private List<Double> data;

    public BarChartMonthObject() {
        super();
    }

    public BarChartMonthObject(List<Double> data) {
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
        return "BarChartMonthObject{" +
                "data=" + data +
                '}';
    }
}
