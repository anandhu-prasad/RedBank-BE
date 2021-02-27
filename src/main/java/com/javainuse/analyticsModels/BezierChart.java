package com.javainuse.analyticsModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BezierChart {
    private final List<String> labels = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June","July","August","September","October","November","December"));
    private List<DatasetObject> dataset;
    private final List<String> legend = new ArrayList<>(Arrays.asList("UNITS"));

    public BezierChart() {
        super();
    }

    public BezierChart(List<DatasetObject> dataset) {
        this.dataset = dataset;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<DatasetObject> getDataset() {
        return dataset;
    }

    public void setDataset(List<DatasetObject> dataset) {
        this.dataset = dataset;
    }

    public List<String> getLegend() {
        return legend;
    }

    @Override
    public String toString() {
        return "BezierChart{" +
                "labels=" + labels +
                ", dataset=" + dataset +
                ", legend=" + legend +
                '}';
    }
}
