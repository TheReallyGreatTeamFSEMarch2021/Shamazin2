package com.talentpath.shamazin.showItemPage.controllers;


import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.services.ItemFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/itemFamily")
public class ItemFamilyController {
    @Autowired
    ItemFamilyService service;

    @GetMapping("/getAll")
    public List<ItemFamily> getAllItemFamilies() {
        return service.getAllItemFamilies();
    }

}
