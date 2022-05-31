package com.example.nordicmotorhomef4final.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class MainController {
// the single method of the MainController that takes us to the homepage
    @GetMapping("/")
    public String showIndex() {
        return "/homepage/index";
    }
}
