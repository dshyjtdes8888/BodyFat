package com.example.service.Controller;

import com.example.service.Model.BodyFat;
import com.example.service.Model.LinearRegressionResult;
import com.example.service.Service.SService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ServiceMapper {
    @Autowired
    public SService service;

    @CrossOrigin(origins = "http://localhost:8282")
    @RequestMapping(value = "/getAll", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<BodyFat> getAll() {
        List<BodyFat> bodyFats =service.findAll();
        return bodyFats;
    }

    @CrossOrigin(origins = "http://localhost:8282")
    @RequestMapping(value = "/calculate", produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String calculate(
            @RequestParam String dataName,
            @RequestParam String calculationType,
            @RequestParam(required = false) Double percentile
    ) {
        double result = 0.0;

        switch (calculationType) {
            case "mean":
                result = service.calculateMean(dataName);
                break;
            case "mode":
                result = service.calculateMode(dataName);
                break;
            case "median":
                result = service.calculateMedian(dataName);
                break;
            case "stdDev":
                result = service.calculateStandardDeviation(dataName);
                break;
            case "q1":
                result = service.calculateQ1(dataName);
                break;
            case "q3":
                result = service.calculateQ3(dataName);
                break;
            case "iqr":
                result = service.calculateIQR(dataName);
                break;
            case "percentile":
                if (percentile != null) {
                    result = service.calculatePercentile(dataName, percentile);
                }
                break;
            default:
                // Handle invalid calculationType
                return "Invalid calculationType";
        }

        // 返回结果，可以根据需要修改格式
        return String.valueOf(result);
    }

    @CrossOrigin(origins = "http://localhost:8282")
    @RequestMapping(value = "/linearRegression", produces = "application/json; charset=utf-8")
    @ResponseBody
    public LinearRegressionResult linearRegression(
            @RequestParam String xField,
            @RequestParam String yField
    ) {
        return service.performLinearRegression(xField, yField);
    }
}
