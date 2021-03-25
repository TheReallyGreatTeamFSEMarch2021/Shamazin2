package com.talentpath.shamazin.showItemPage.controllers;


import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemFamilyException;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.services.ItemFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/{id}")
    public ItemFamily getFamilyById(@PathVariable Integer id) throws NoSuchItemFamilyException {
        return service.getItemFamilyById(id);
    }

    @GetMapping("/getByItemId/{id}")
    public ItemFamily getByItemId(@PathVariable Integer id) {
        return service.getItemFamilyByItemId(id);
    }

}
