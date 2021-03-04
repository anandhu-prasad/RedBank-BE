package com.javainuse.analyticsModels.charts;

import com.javainuse.analyticsModels.objects.BarChartMonthObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarChartMonth {
    private final List<String> labels = new ArrayList<>(Arrays.asList("A+","A-", "B+","B-","AB+", "AB-", "O+","O-"));
    private List<BarChartMonthObject> datasets;

    public BarChartMonth() {
        super();
    }

    public BarChartMonth(List<BarChartMonthObject> datasets) {
        this.datasets = datasets;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<BarChartMonthObject> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<BarChartMonthObject> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return "barChartMonth{" +
                "labels=" + labels +
                ", datasets=" + datasets +
                '}';
    }
}
