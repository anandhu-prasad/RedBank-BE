package com.javainuse.analyticsModels;

public class PieChart {
    private  String name;
    private  Integer population;
    private String color;
    private String legendFontColor;
    private Integer legendFontSize;

    public PieChart() {
        super();
    }

    public PieChart(String name, Integer population, String color, String legendFontColor, Integer legendFontSize) {
        this.name = name;
        this.population = population;
        this.color = color;
        this.legendFontColor = legendFontColor;
        this.legendFontSize = legendFontSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLegendFontColor() {
        return legendFontColor;
    }

    public void setLegendFontColor(String legendFontColor) {
        this.legendFontColor = legendFontColor;
    }

    public Integer getLegendFontSize() {
        return legendFontSize;
    }

    public void setLegendFontSize(Integer legendFontSize) {
        this.legendFontSize = legendFontSize;
    }

    @Override
    public String toString() {
        return "PieChart{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", color='" + color + '\'' +
                ", legendFontColor='" + legendFontColor + '\'' +
                ", legendFontSize=" + legendFontSize +
                '}';
    }
}
