package com.javainuse.responses;

public class TodaysSale_RespBody {
    private Integer unitsSold;
    private Double amountCollected;
    private Integer unitsBought;
    private Double amountSpent;

    public TodaysSale_RespBody() {
        super();
    }

    public TodaysSale_RespBody(Integer unitsSold, Double amountCollected, Integer unitsBought, Double amountSpent) {
        this.unitsSold = unitsSold;
        this.amountCollected = amountCollected;
        this.unitsBought = unitsBought;
        this.amountSpent = amountSpent;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public Integer getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(Integer unitsSold) {
        this.unitsSold = unitsSold;
    }

    public Double getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(Double amountCollected) {
        this.amountCollected = amountCollected;
    }

    public Integer getUnitsBought() {
        return unitsBought;
    }

    public void setUnitsBought(Integer unitsBought) {
        this.unitsBought = unitsBought;
    }

    @Override
    public String toString() {
        return "TodaysSale_RespBody{" +
                "unitsSold=" + unitsSold +
                ", amountCollected=" + amountCollected +
                ", unitsBought=" + unitsBought +
                ", amountSpent=" + amountSpent +
                '}';
    }
}
