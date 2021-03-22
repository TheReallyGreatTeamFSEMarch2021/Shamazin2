package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.daos.ProductPhotoRepository;
import com.talentpath.shamazin.showItemPage.exceptions.*;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ProductPhotoService {
    @Autowired
    ProductPhotoRepository productPhotoRepo;
    @Autowired
    ItemRepository itemRepo;

    public ProductPhoto getProductPhotoByID(Integer productPhotoID) throws NoSuchElementException, InvalidIDException {
        if(productPhotoID<1)throw new InvalidIDException("productPhotoID has to be a positive integer.");
        Optional<ProductPhoto> productPhoto = productPhotoRepo.findById(productPhotoID);
        if(productPhoto.isEmpty()) throw new NoSuchElementException("No productPhoto with id: " + productPhotoID + " exists!");
        else return productPhoto.get();
    }

    public ProductPhoto addProductPhoto(ProductPhoto toAdd) throws NullProductPhotoException, NullArgumentException{
        if(toAdd==null)throw new NullProductPhotoException("Cannot add a null product photo");
        if(toAdd.getPhotoURL()==null)throw new NullArgumentException("Cannot add a product photo with null photoURL");
        if(toAdd.getItem()==null)throw new NullArgumentException("Cannot add a product photo with null item_id");
        return productPhotoRepo.saveAndFlush(toAdd);
    }

    public ProductPhoto editProductPhoto(ProductPhoto editedPhoto){return productPhotoRepo.saveAndFlush(editedPhoto);}

    public void deleteProductPhoto(Integer productPhotoID){productPhotoRepo.deleteById(productPhotoID);}

    public List<ProductPhoto> getProductPhotosByItemId(Integer itemID) throws NoSuchItemException, NullArgumentException {
        if(itemID==null) throw new NullArgumentException("Null itemId passed to getProductPhotosByItemId in ProductPhotoService.");
        else if(!itemRepo.existsById(itemID)) throw new NoSuchItemException("No item with id: " + itemID + " exists!");
        return productPhotoRepo.findAllByItemId(itemID);
    }
}
