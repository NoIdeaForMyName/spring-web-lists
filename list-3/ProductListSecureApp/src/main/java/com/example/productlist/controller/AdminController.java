package com.example.productlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
public class AdminController {
    @GetMapping({"/index","","/"})
    public String adminHome() {
        return "/adm/index";
    }
}
