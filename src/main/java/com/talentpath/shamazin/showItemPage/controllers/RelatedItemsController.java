package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.services.RelatedBoughtItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping("/api/related")
@RestController
public class RelatedItemsController {

    @Autowired
    RelatedBoughtItemService relatedBoughtItemService;

    @GetMapping("/bought/{itemId}")
    public Set<Item> getRelatedBoughtItems(@PathVariable Integer itemId) {
        return relatedBoughtItemService.getAllRelatedBoughtItems(itemId);
    }

}
