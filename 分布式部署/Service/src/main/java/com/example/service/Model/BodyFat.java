package com.example.service.Model;

import lombok.Data;

@Data
public class BodyFat {
    private int ID;
    private double Density;
    private double PctBF;
    private int Age;
    private double Weight;
    private double Height;
    private double Neck;
    private double Chest;
    private double Abdomen;
    private double Waist;
    private double Hip;
    private double Thigh;
    private double Knee;
    private double Ankle;
    private double Bicep;
    private double Forearm;
    private double Wrist;

    @Override
    public String toString() {
        return "BodyFat{" +
                "ID=" + ID +
                ", Density='" + Density + '\'' +
                ", PctBF=" + PctBF +
                ", Age=" + Age +
                ", Weight=" + Weight +
                ", Height=" + Height +
                ", Neck=" + Neck +
                ", Chest=" + Chest +
                ", Abdomen=" + Abdomen +
                ", Waist=" + Waist +
                ", Hip=" + Hip +
                ", Thigh=" + Thigh +
                ", Knee=" + Knee +
                ", Ankle=" + Ankle +
                ", Bicep=" + Bicep +
                ", Forearm=" + Forearm +
                ", Wrist=" + Wrist +
                '}';
    }
}
