package com.talentpath.shamazin.showItemPage.controllers;


import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import com.talentpath.shamazin.showItemPage.services.ItemService;
import com.talentpath.shamazin.showItemPage.services.ProductPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductPhotoController {
    @Autowired
    ItemService itemService;

    @Autowired
    ProductPhotoService productPhotoServ;

    @GetMapping("/productPhotosForItem/{itemID}")
    public List<ProductPhoto> getProductPhotos(@PathVariable Integer itemID){
        Item item = itemService.findByItemID(itemID);
        System.out.println(item.getName());

        return productPhotoServ.getProductPhotosByItsItem(item);
    }

    @GetMapping("/productPhotos/{productID}")
    public ProductPhoto getProductPhoto(@PathVariable Integer productID){
        return productPhotoServ.getProductPhotoByID(productID);
    }



}