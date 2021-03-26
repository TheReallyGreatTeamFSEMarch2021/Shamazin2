package com.talentpath.shamazin.showItemPage.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/userdata")
public class UserDataController {
    @GetMapping("/test")
    public String testUserDataController(){
        return "should see auth this!";
    }
}
