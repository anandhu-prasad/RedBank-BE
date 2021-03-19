package com.javainuse.analyticsModels.objects;

import java.util.List;

public class NewBarChart {
    private List<Double> bloodObject;
    private List<Double> plasmaObject;
    private List<Double> plateletObject;
    private List<Double> total;

    public NewBarChart() {
        super();
    }

    public NewBarChart(List<Double> bloodObject, List<Double> plasmaObject, List<Double> plateletObject, List<Double> total) {
        this.bloodObject = bloodObject;
        this.plasmaObject = plasmaObject;
        this.plateletObject = plateletObject;
        this.total = total;
    }

    public List<Double> getBloodObject() {
        return bloodObject;
    }

    public void setBloodObject(List<Double> bloodObject) {
        this.bloodObject = bloodObject;
    }

    public List<Double> getPlasmaObject() {
        return plasmaObject;
    }

    public void setPlasmaObject(List<Double> plasmaObject) {
        this.plasmaObject = plasmaObject;
    }

    public List<Double> getPlateletObject() {
        return plateletObject;
    }

    public void setPlateletObject(List<Double> plateletObject) {
        this.plateletObject = plateletObject;
    }

    public List<Double> getTotal() {
        return total;
    }

    public void setTotal(List<Double> total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "NewBarChart{" +
                "bloodObject=" + bloodObject +
                ", plasmaObject=" + plasmaObject +
                ", plateletObject=" + plateletObject +
                ", total=" + total +
                '}';
    }
}
