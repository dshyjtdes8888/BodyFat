package com.example.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mapper {
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
}
