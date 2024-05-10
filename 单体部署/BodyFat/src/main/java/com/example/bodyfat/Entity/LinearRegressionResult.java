package com.example.bodyfat.Entity;

import lombok.Getter;

@Getter
public class LinearRegressionResult {
    private double correlation;
    private double slope;
    private double intercept;


    public void setCorrelation(double correlation) {
        this.correlation = correlation;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }
}
