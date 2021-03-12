package com.talentpath.shamazin.showItemPage.controllers;


import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
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
@CrossOrigin(origins={"http://localhost:3000", "http://localhost:4200"}) //3000 for React apps, 4200 for Angular apps
public class ProductPhotoController {
    @Autowired
    ItemService itemService;

    @Autowired
    ProductPhotoService productPhotoServ;

    @GetMapping("/")
    public String test(){
        return "SUCCESSFULLY RAN BACKEND REST API";
    }

    @GetMapping("/productPhotosForItem/{itemID}")
    public List<ProductPhoto> getProductPhotos(@PathVariable Integer itemID) throws NoSuchItemException {
        Item item = itemService.getItem(itemID);
        return productPhotoServ.getProductPhotosByItsItem(item);
    }

    @GetMapping("/productPhotos/{productID}")
    public ProductPhoto getProductPhoto(@PathVariable Integer productID){
        return productPhotoServ.getProductPhotoByID(productID);
    }



}
