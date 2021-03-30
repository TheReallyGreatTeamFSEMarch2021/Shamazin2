package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.exceptions.NoSuchInfoException;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.models.Info;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.services.InfoService;
import com.talentpath.shamazin.showItemPage.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/info")
public class InfoController {

    @Autowired
    InfoService service;

    @GetMapping("/getAll")
    public List<Info> getAllItems() {
        return service.getAllInfo();
    }

    @GetMapping("/get/{id}")
    public Info getInfoById(@PathVariable Integer id) throws  NoSuchInfoException {
        return service.getInfo(id);
    }

    @GetMapping("/getByItemId/{itemId}")
    public List<Info> getInfosByItemId(@PathVariable Integer itemId) {

        return service.getAllByItem(itemId);
    }


}
