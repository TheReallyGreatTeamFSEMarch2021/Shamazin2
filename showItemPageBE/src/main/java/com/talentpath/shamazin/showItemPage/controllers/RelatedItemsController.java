package com.talentpath.shamazin.showItemPage.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/related")
@RestController
public class RelatedItemsController {

    @GetMapping("/bought/{itemId}")
    public String getRelatedBoughtItems(@PathVariable Integer itemId) {
        String message = "Currently looking for items that users also bought alongside item no. "
                + itemId + "...";
        return message;
    }
}
