package com.illan.ulearnvite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageNotFoundController {

    @GetMapping("/404")
    public String showNotFoundPage(){
        return "pages/not-found";
    }

}
