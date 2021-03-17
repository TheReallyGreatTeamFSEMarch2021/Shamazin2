package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ProductPhotoRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.exceptions.NullItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullProductPhotoException;
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

    public List<ProductPhoto> getProductPhotosByItsItem(Item item) throws NullItemException {
        if(item==null){
            throw new NullItemException("Received null item in service method getProductPhotosByItsItem");
        }

        Optional<List<ProductPhoto>> productPhotos = productPhotoRepo.findByItem(item);
        if(productPhotos.isPresent()){
            return productPhotos.get();
        }else{
            return new ArrayList<ProductPhoto>();
        }

//        try{
//            productPhotos.get().get(0).getPhotoURL();
//        }catch(NoSuchElementException ex){
//            System.out.println("No product photos associated with that item.  " + ex);
//        }
//
//        return productPhotos.get();
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

    public ProductPhoto addProductPhoto(ProductPhoto toAdd) throws NullProductPhotoException, NullArgumentException{
        if(toAdd==null)throw new NullProductPhotoException("Cannot add a null product photo");
        if(toAdd.getId()==null)throw new NullArgumentException("Cannot add a product photo with null ID");
        if(toAdd.getPhotoURL()==null)throw new NullArgumentException("Cannot add a product photo with null photoURL");
        if(toAdd.getItem()==null)throw new NullArgumentException("Cannot add a product photo with null item_id");
        return productPhotoRepo.saveAndFlush(toAdd);
    }

    public ProductPhoto editProductPhoto(ProductPhoto editedPhoto){return productPhotoRepo.saveAndFlush(editedPhoto);}

    public void deleteProductPhoto(Integer productPhotoID){productPhotoRepo.deleteById(productPhotoID);}


    @Transactional
    public void truncateProductPhotos() {
        productPhotoRepo.truncateTable();
    }



}
