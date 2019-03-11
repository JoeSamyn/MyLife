package com.joesamyn.MyLife.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @RequestMapping("/categories")
    public String showAll(Model model){

        return "category/details";
    }

    @RequestMapping("/categories/{catId}")
    public String detail(@PathVariable Long catId){
        return "/categories/details";
    }
}
