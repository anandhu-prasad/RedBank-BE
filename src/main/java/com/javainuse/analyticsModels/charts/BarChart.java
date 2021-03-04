package com.javainuse.analyticsModels.charts;

import com.javainuse.analyticsModels.objects.BarChartObject;
import com.javainuse.analyticsModels.objects.DatasetObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarChart {
    private final List<String> labels = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June","July","August","September","October","November","December"));
    private List<BarChartObject> datasets;

    public BarChart() {
        super();
    }

    public BarChart(List<BarChartObject> datasets) {
        this.datasets = datasets;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<BarChartObject> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<BarChartObject> datasets) {
        this.datasets = datasets;
    }

    @Override
    public String toString() {
        return "BarChart{" +
                "labels=" + labels +
                ", datasets=" + datasets +
                '}';
    }
}
