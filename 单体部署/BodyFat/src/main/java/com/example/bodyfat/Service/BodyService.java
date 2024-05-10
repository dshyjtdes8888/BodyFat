package com.example.bodyfat.Service;


import com.example.bodyfat.Entity.LinearRegressionResult;
import com.example.bodyfat.Entity.BodyFat;
import com.example.bodyfat.Mapper.BodyFatMapper;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BodyService {
    @Autowired(required = false)
    private BodyFatMapper bodyFatMapper ;

    // 获取所有数据
    public List<BodyFat> findAll() {
        return bodyFatMapper.findAll();
    }

    // 计算平均数
    public double calculateMean(String dataName) {
        List<BodyFat> dataList = findAll();
        double sum = dataList.stream()
                .mapToDouble(bodyFat -> getValueByDataName(bodyFat, dataName))
                .sum();
        return sum / dataList.size();
    }

    // 计算众数
    public double calculateMode(String dataName) {
        List<BodyFat> dataList = findAll();
        List<Double> values = dataList.stream()
                .map(bodyFat -> getValueByDataName(bodyFat, dataName))
                .collect(Collectors.toList());

        // 找出众数
        long maxCount = 0;
        double mode = 0;
        for (Double value : values) {
            long count = Collections.frequency(values, value);
            if (count > maxCount) {
                maxCount = count;
                mode = value;
            }
        }
        return mode;
    }

    // 计算中位数
    public double calculateMedian(String dataName) {
        List<BodyFat> dataList = findAll();
        List<Double> values = dataList.stream()
                .map(bodyFat -> getValueByDataName(bodyFat, dataName))
                .sorted()
                .collect(Collectors.toList());

        int size = values.size();
        if (size % 2 == 0) {
            // 偶数个元素取中间两个的平均值
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            // 奇数个元素直接取中间值
            return values.get(size / 2);
        }
    }

    // 计算标准差
    public double calculateStandardDeviation(String dataName) {
        List<BodyFat> dataList = findAll();
        double[] values = dataList.stream()
                .mapToDouble(bodyFat -> getValueByDataName(bodyFat, dataName))
                .toArray();

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(values);
        return descriptiveStatistics.getStandardDeviation();
    }

    // 计算第一四分位数
    public double calculateQ1(String dataName) {
        List<BodyFat> dataList = findAll();
        double[] values = dataList.stream()
                .mapToDouble(bodyFat -> getValueByDataName(bodyFat, dataName))
                .toArray();

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(values);
        return descriptiveStatistics.getPercentile(25);
    }

    // 计算第三四分位数
    public double calculateQ3(String dataName) {
        List<BodyFat> dataList = findAll();
        double[] values = dataList.stream()
                .mapToDouble(bodyFat -> getValueByDataName(bodyFat, dataName))
                .toArray();

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(values);
        return descriptiveStatistics.getPercentile(75);
    }

    // 计算四分位数范围 (IQR)
    public double calculateIQR(String dataName) {
        return calculateQ3(dataName) - calculateQ1(dataName);
    }

    // 计算百分位数
    public double calculatePercentile(String dataName, double percentile) {
        List<BodyFat> dataList = findAll();
        double[] values = dataList.stream()
                .mapToDouble(bodyFat -> getValueByDataName(bodyFat, dataName))
                .toArray();

        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(values);
        return descriptiveStatistics.getPercentile(percentile);
    }

    // 执行线性回归
    public LinearRegressionResult performLinearRegression(String xFieldName, String yFieldName) {
        List<BodyFat> dataList = findAll();
        double[] xValues = dataList.stream()
                .mapToDouble(bodyFat -> getValueByDataName(bodyFat, xFieldName))
                .toArray();
        double[] yValues = dataList.stream()
                .mapToDouble(bodyFat -> getValueByDataName(bodyFat, yFieldName))
                .toArray();

        SimpleRegression regression = new SimpleRegression();
        for (int i = 0; i < xValues.length; i++) {
            regression.addData(xValues[i], yValues[i]);
        }

        LinearRegressionResult result = new LinearRegressionResult();
        result.setCorrelation(regression.getR());
        result.setSlope(regression.getSlope());
        result.setIntercept(regression.getIntercept());

        return result;
    }

    // 根据数据名获取对应的值
    private double getValueByDataName(BodyFat bodyFat, String dataName) {
        switch (dataName) {
            case "age":
                return bodyFat.getAge();
            case "density":
                return bodyFat.getDensity();
            case "pctBF":
                return bodyFat.getPctBF();
            case "weight":
                return bodyFat.getWeight();
            case "height":
                return bodyFat.getHeight();
            case "neck":
                return bodyFat.getNeck();
            case "chest":
                return bodyFat.getChest();
            case "abdomen":
                return bodyFat.getAbdomen();
            case "waist":
                return bodyFat.getWaist();
            case "hip":
                return bodyFat.getHip();
            case "thigh":
                return bodyFat.getThigh();
            case "knee":
                return bodyFat.getKnee();
            case "ankle":
                return bodyFat.getAnkle();
            case "bicep":
                return bodyFat.getBicep();
            case "forearm":
                return bodyFat.getForearm();
            case "wrist":
                return bodyFat.getWrist();

            // 其他数据名的处理
            default:
                throw new IllegalArgumentException("Invalid dataName: " + dataName);
        }
    }
}

