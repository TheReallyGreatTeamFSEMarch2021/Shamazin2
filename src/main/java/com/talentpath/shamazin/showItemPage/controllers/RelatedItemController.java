package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.services.RelatedBoughtItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/related")
@RestController
public class RelatedItemController {

    @Autowired
    RelatedBoughtItemService relatedBoughtItemService;

    @GetMapping("/bought/{itemId}")
    public Set<Item> getRelatedBoughtItems(@PathVariable Integer itemId) {
        return relatedBoughtItemService.getAllRelatedBoughtItems(itemId);
    }

    @GetMapping("/bought/{itemId}/{page}")
    public List<Item> getRelatedBoughtItemsByPage(@PathVariable Integer itemId, @PathVariable Integer page) {
        return relatedBoughtItemService.getAllRelatedBoughtItemsByPage(itemId, page);
    }

    @DeleteMapping("/bought/{itemId}")
    public void deleteRelatedBoughtItems(@PathVariable Integer itemId) {
        relatedBoughtItemService.deleteAllRelatedBoughtItems(itemId);
    }
}