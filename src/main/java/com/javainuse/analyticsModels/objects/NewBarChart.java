package com.javainuse.analyticsModels.objects;

import java.util.List;

public class NewBarChart {
    private List<Double> bloodObject;
    private List<Double> plasmaObject;
    private List<Double> plateletObject;


    public NewBarChart() {
        super();
    }

    public NewBarChart(List<Double> bloodObject, List<Double> plasmaObject, List<Double> plateletObject) {
        this.bloodObject = bloodObject;
        this.plasmaObject = plasmaObject;
        this.plateletObject = plateletObject;

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

    @Override
    public String toString() {
        return "NewBarChart{" +
                "bloodObject=" + bloodObject +
                ", plasmaObject=" + plasmaObject +
                ", plateletObject=" + plateletObject +
                '}';
    }
}
