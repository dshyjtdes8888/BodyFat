package com.example.bodyfat.Controller;

import com.example.bodyfat.Entity.BodyFat;
import com.example.bodyfat.Entity.LinearRegressionResult;
import com.example.bodyfat.Service.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BodyFatController {
    @Autowired
    private BodyService bodyService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/analysis1")
    public String analysis1() {
        return "analysis1";
    }

    @GetMapping("/analysis2")
    public String analysis2() {
        return "analysis2";
    }

    @RequestMapping(value = "/getAll", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<BodyFat> getAll() {
        List<BodyFat> bodyFats = bodyService.findAll();
        return bodyFats;
    }

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
                result = bodyService.calculateMean(dataName);
                break;
            case "mode":
                result = bodyService.calculateMode(dataName);
                break;
            case "median":
                result = bodyService.calculateMedian(dataName);
                break;
            case "stdDev":
                result = bodyService.calculateStandardDeviation(dataName);
                break;
            case "q1":
                result = bodyService.calculateQ1(dataName);
                break;
            case "q3":
                result = bodyService.calculateQ3(dataName);
                break;
            case "iqr":
                result = bodyService.calculateIQR(dataName);
                break;
            case "percentile":
                if (percentile != null) {
                    result = bodyService.calculatePercentile(dataName, percentile);
                }
                break;
            default:
                // Handle invalid calculationType
                return "Invalid calculationType";
        }

        // 返回结果，可以根据需要修改格式
        return String.valueOf(result);
    }

    @RequestMapping(value = "/linearRegression", produces = "application/json; charset=utf-8")
    @ResponseBody
    public LinearRegressionResult linearRegression(
            @RequestParam String xField,
            @RequestParam String yField
    ) {
        return bodyService.performLinearRegression(xField, yField);
    }
}
