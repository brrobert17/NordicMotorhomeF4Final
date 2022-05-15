package com.example.nordicmotorhomef4final.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class MainController {

    @GetMapping("/")
    public String showIndex() {
        return "/homepage/index";
    }
}
