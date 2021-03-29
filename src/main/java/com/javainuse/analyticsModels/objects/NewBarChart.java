package com.javainuse.analyticsModels.objects;

import java.util.List;

public class NewBarChart {
    private List<Double> bloodObject;
    private List<Double> plasmaObject;
    private List<Double> plateletObject;
    private Double totalBlood;
    private Double totalPlasma;
    private Double totalPlatelet;


    public NewBarChart() {
        super();
    }

    public NewBarChart(List<Double> bloodObject, List<Double> plasmaObject, List<Double> plateletObject, Double totalBlood, Double totalPlasma, Double totalPlatelet) {
        this.bloodObject = bloodObject;
        this.plasmaObject = plasmaObject;
        this.plateletObject = plateletObject;
        this.totalBlood = totalBlood;
        this.totalPlasma = totalPlasma;
        this.totalPlatelet = totalPlatelet;
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

    public Double getTotalBlood() {
        return totalBlood;
    }

    public void setTotalBlood(Double totalBlood) {
        this.totalBlood = totalBlood;
    }

    public Double getTotalPlasma() {
        return totalPlasma;
    }

    public void setTotalPlasma(Double totalPlasma) {
        this.totalPlasma = totalPlasma;
    }

    public Double getTotalPlatelet() {
        return totalPlatelet;
    }

    public void setTotalPlatelet(Double totalPlatelet) {
        this.totalPlatelet = totalPlatelet;
    }

    @Override
    public String toString() {
        return "NewBarChart{" +
                "bloodObject=" + bloodObject +
                ", plasmaObject=" + plasmaObject +
                ", plateletObject=" + plateletObject +
                ", totalBlood=" + totalBlood +
                ", totalPlasma=" + totalPlasma +
                ", totalPlatelet=" + totalPlatelet +
                '}';
    }
}
