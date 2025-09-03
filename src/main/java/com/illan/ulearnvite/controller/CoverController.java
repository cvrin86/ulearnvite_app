package com.illan.ulearnvite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoverController {

    @GetMapping("/cover")
    public String pageCover() {

		return "cover/cover-page";
    }

}
