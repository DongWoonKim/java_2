package com.example.spring.webfrontservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webs")
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
