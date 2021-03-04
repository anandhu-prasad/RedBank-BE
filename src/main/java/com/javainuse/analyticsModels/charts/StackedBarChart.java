package com.javainuse.analyticsModels.charts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackedBarChart {
    private final List<String> labels = new ArrayList<>(Arrays.asList("A+","A-","B+","B-","AB+","AB-","O+","0-"));
    private final List<String> lagend = new ArrayList<>(Arrays.asList("A+","A-","B+","B-","AB+","AB-","O+","0-"));
    private List<List<Integer>> data;
    private final List<String> barColors = new ArrayList<>(Arrays.asList("#dfe4ea", "#ced6e0", "#a4b0be"));

    public StackedBarChart() {
        super();
    }

    public StackedBarChart(List<List<Integer>> data) {
        this.data = data;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<String> getLagend() {
        return lagend;
    }

    public List<List<Integer>> getData() {
        return data;
    }

    public void setData(List<List<Integer>> data) {
        this.data = data;
    }

    public List<String> getBarColors() {
        return barColors;
    }

    @Override
    public String toString() {
        return "StackedBarChart{" +
                "labels=" + labels +
                ", lagend=" + lagend +
                ", data=" + data +
                ", barColors=" + barColors +
                '}';
    }
}
