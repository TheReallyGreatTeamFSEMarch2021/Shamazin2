package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    ItemService service;

    @GetMapping("/getAll")
    public List<Item> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/get/{id}")
    public Item getItemById(@PathVariable Integer id) throws NoSuchItemException, NullArgumentException {
        return service.getItem(id);
    }

    @GetMapping("/getByFamilyId/{familyId}")
    public List<Item> getItemsByFamilyId(@PathVariable Integer familyId) {
        return service.findByFamilyId(familyId);
    }

    @GetMapping("/getFamilyId/{itemId}")
    public Integer getFamilyId(@PathVariable Integer itemId) throws NoSuchItemException {
        return service.getFamilyId(itemId);
    }

}
