package com.sf.skytalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish(){
        System.out.println("publish");
        return "publish";
    }
}
