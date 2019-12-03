package com.gw.dev.hcbq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home/")
public class IndexController {
    @RequestMapping("homepage1")
    public String index(){
        return "home/homepage1";
    }
}
