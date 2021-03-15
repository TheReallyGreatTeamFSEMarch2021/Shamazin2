package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ProductPhotoRepository;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ProductPhotoService {
    @Autowired
    ProductPhotoRepository productPhotoRepo;

    public List<ProductPhoto> getProductPhotosByItsItem(Item item){
        Optional<List<ProductPhoto>> productPhotos = productPhotoRepo.findByItem(item);
        try{
            productPhotos.get().get(0).getPhotoURL();
        }catch(NoSuchElementException ex){
            System.out.println("No product photos associated with that item.  " + ex);
        }

        return productPhotos.get();

    }

    public ProductPhoto getProductPhotoByID(Integer productPhotoID) {
        Optional<ProductPhoto> productPhoto = productPhotoRepo.findById(productPhotoID);
        try{
            productPhoto.get();
        }catch(NoSuchElementException ex){
            System.out.println(("No product photo associated with that product ID. " + ex));
        }
        return productPhoto.get();
    }

    public ProductPhoto addProductPhoto(ProductPhoto toAdd){return productPhotoRepo.saveAndFlush(toAdd);}

    public ProductPhoto editProductPhoto(ProductPhoto editedPhoto){return productPhotoRepo.saveAndFlush(editedPhoto);}

    public void deleteProductPhoto(Integer productPhotoID){productPhotoRepo.deleteById(productPhotoID);}


    @Transactional
    public void truncateProductPhotos() {
        productPhotoRepo.truncateTable();
    }



}
