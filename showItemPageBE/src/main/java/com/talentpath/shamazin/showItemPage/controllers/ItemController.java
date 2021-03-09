package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ItemController {
    @Autowired
    ItemService service;

}
