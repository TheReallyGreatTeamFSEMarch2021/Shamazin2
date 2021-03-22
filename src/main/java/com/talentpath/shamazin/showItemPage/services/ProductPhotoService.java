package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ProductPhotoRepository;
import com.talentpath.shamazin.showItemPage.exceptions.InvalidIDException;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.exceptions.NullItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullProductPhotoException;
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

    public List<ProductPhoto> getProductPhotosByItsItem(Item item) throws NullItemException, NullArgumentException {
        if(item==null){
            throw new NullItemException("Received null item in service method getProductPhotosByItsItem");
        }
        if(item.getId()==null || item.getItemFamily()==null || item.getName()==null || item.getPrice()==null || item.getStockRemaining()==null || item.getPrimeEligible()==null){
            throw new NullArgumentException("In 'getProductPhotosByItsItem' method, item passed in has at least one null argument for required property.");
        }

        Optional<List<ProductPhoto>> productPhotos = productPhotoRepo.findByItem(item);
        if(productPhotos.isPresent()){
            return productPhotos.get();
        }else{
            return new ArrayList<ProductPhoto>();
        }

    }


    public ProductPhoto getProductPhotoByID(Integer productPhotoID) throws InvalidIDException {
        if(productPhotoID<1)throw new InvalidIDException("productPhotoID has to be a positive integer.");
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
        if(toAdd.getPhotoURL()==null)throw new NullArgumentException("Cannot add a product photo with null photoURL");
        if(toAdd.getItem()==null)throw new NullArgumentException("Cannot add a product photo with null item_id");
        return productPhotoRepo.saveAndFlush(toAdd);
    }

    public ProductPhoto editProductPhoto(ProductPhoto editedPhoto){return productPhotoRepo.saveAndFlush(editedPhoto);}

    public void deleteProductPhoto(Integer productPhotoID){productPhotoRepo.deleteById(productPhotoID);}

}
