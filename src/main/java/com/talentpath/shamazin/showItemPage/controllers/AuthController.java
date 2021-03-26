package com.talentpath.shamazin.showItemPage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/api/auth/")
public class AuthController {

    @GetMapping("test")
    public String testSec(){
        return "Can reach auth controller. yes yes yes ";
    }

}
