package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ProductPhotoRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.exceptions.NullItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullProductPhotoException;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import com.talentpath.shamazin.showItemPage.models.Property;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"servicetesting"})
public class productPhotoServiceTest {

    @Autowired
    ItemService itemServ;

    @Autowired
    ItemFamilyService itemFamServ;

    @Autowired
    ProductPhotoService productPhotoServ;

    @Autowired
    ProductPhotoRepository productPhotoRepo;

    @BeforeEach
    void deleteAllItems() throws NullArgumentException {
        itemFamServ.truncateItemFamily();
        ItemFamily toAddItemFamily = new ItemFamily();
        toAddItemFamily.setFamilyName("Short-Sleeves");
        toAddItemFamily.setBrand("Addidas");
        ItemFamily itemFamily1 = itemFamServ.addItemFamily(toAddItemFamily);

        //add couple of items to Item table
        Item item1 = new Item(itemFamily1, "Blue Addidas T-Shirt",20.75, 30, true );
        Item item2 = new Item(itemFamily1, "Green Addidas T-Shirt",22.75, 30, true );
        itemServ.addItem(item1);
        itemServ.addItem(item2);
    }

    //golden path test
    @Test
    @Transactional
    void addProductPhoto() throws NullArgumentException, NullProductPhotoException {
        //Arrange
        //Act
        List<Item> items = itemServ.getAllItems();
        Item item = items.get(0);
//        System.out.println(item.getName());

        try{
            List<ProductPhoto> productPhotos = productPhotoServ.getProductPhotosByItsItem(item);
            assertEquals(0, productPhotos.size());

            //Assert
            //Adding a ProductPhoto, size should increase
            ProductPhoto productPhotoToAdd = new ProductPhoto("photo1URL", item);
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            productPhotos = productPhotoServ.getProductPhotosByItsItem(item);
            assertEquals(1, productPhotos.size());
            assertEquals("photo1URL", productPhotos.get(0).getPhotoURL());
            assertEquals("Blue Addidas T-Shirt", productPhotos.get(0).getItem().getName());

        }catch(Exception ex) {
            fail("Golden path test for addProductPhoto. Should not have triggered any exception");
        }

    }

    @Test
    void addNullProductPhoto() throws NullProductPhotoException, NullArgumentException {
        ProductPhoto productPhotoToAdd = null;
        try{
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            fail("In addNullProductPhoto, should have caught nullProductPhotoException.");
        }catch(NullProductPhotoException ex) {
            System.out.println("Failed correctly, caught the NullProductPhotoException. " + ex.getMessage());
        }catch(Exception ex){
            fail("should not run into another exception besides NullProductPhotoException. "+  ex);
        }
    }

    @Test
    void addProductPhotoNullPhotoURL() throws NullProductPhotoException, NullArgumentException {
        ProductPhoto productPhotoToAdd = new ProductPhoto();
        Item item = new Item();
        productPhotoToAdd.setItem(item);
        productPhotoToAdd.setId(3);
        try{
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            fail("In addNullProductPhoto, should have caught NullArgumentException.");
        }catch(NullArgumentException ex){
            System.out.println("Failed correctly, caught the NullArgumentException. " + ex.getMessage());
        }catch(Exception ex){
            fail("should not run into another exception besides NullArgumentException. "+  ex);

        }
    }

    @Test
    void addProductPhotoNullID() throws NullProductPhotoException, NullArgumentException {
        ProductPhoto productPhotoToAdd = new ProductPhoto();
        Item item = new Item();
        productPhotoToAdd.setItem(item);
        productPhotoToAdd.setPhotoURL("photoURL");
        try{
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            fail("In addNullProductPhoto, should have caught NullArgumentException.");
        }catch(NullArgumentException ex){
            System.out.println("Failed correctly, caught the NullArgumentException. " + ex.getMessage());
        }catch(Exception ex){
            fail("should not run into another exception besides NullArgumentException. "+  ex);

        }
    }

    @Test
    void addProductPhotoNullItem() throws NullProductPhotoException, NullArgumentException {
        ProductPhoto productPhotoToAdd = new ProductPhoto();
        productPhotoToAdd.setId(3);
        productPhotoToAdd.setPhotoURL("photoURL");
        try{
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            fail("In addNullProductPhoto, should have caught NullArgumentException.");
        }catch(NullArgumentException ex){
            System.out.println("Failed correctly, caught the NullArgumentException. " + ex.getMessage());
        }catch(Exception ex){
            fail("should not run into another exception besides NullArgumentException. "+  ex);

        }
    }

    //golden path test
    @Test
    void getProductPhotosByItsItem() throws NullArgumentException {
        Item item = new Item();
        item.setName("Blink Cameras Test");
        Item itemAdded = itemServ.addItem(item);
        try{
            List<ProductPhoto> productPhotos = productPhotoServ.getProductPhotosByItsItem(item);
            assertEquals(0, productPhotos.size());

            ProductPhoto prodPhoto1 = new ProductPhoto("https://favrecipezaws.s3.amazonaws.com/BlinkMiniCameraPicR.jpg", itemAdded);
            ProductPhoto prodPhoto2 = new ProductPhoto("https://favrecipezaws.s3.amazonaws.com/twoBlinkCameras.jpeg",itemAdded);
            productPhotoRepo.saveAndFlush(prodPhoto1);
            productPhotoRepo.saveAndFlush(prodPhoto2);

            productPhotos = productPhotoServ.getProductPhotosByItsItem(item);
            ProductPhoto productPhoto1 = productPhotos.get(0);
            ProductPhoto productPhoto2 = productPhotos.get(1);

            assertEquals(1, prodPhoto1.getId());
            assertEquals(2, prodPhoto2.getId());
            assertEquals("https://favrecipezaws.s3.amazonaws.com/BlinkMiniCameraPicR.jpg", prodPhoto1.getPhotoURL());
            assertEquals("https://favrecipezaws.s3.amazonaws.com/twoBlinkCameras.jpeg", prodPhoto2.getPhotoURL());

        }catch(Exception ex){
            fail("Golden path test for getProductPhotosByItsItem. Should not have triggered any exception");
        }
    }

    @Test
    void getProductPhotosByItsItemNullItem() throws NullItemException {
        Item item = null;
        try {
            List<ProductPhoto> productPhotos = productPhotoServ.getProductPhotosByItsItem(item);
            assertEquals(0, productPhotos.size());

        } catch (NullItemException ex) {
            System.out.println("In getProductPhotosByItsItemNullItem, failed correctly, caught the NullItemException. " + ex.getMessage());
        } catch (Exception ex) {
            fail("In getProductPhotosByItsItemNullItem, should not run into another exception besides NullItemException. " + ex);
        }
    }

    //golden path test
    @Test
    void getProductPhotoByID(){

    }

    //golden path test
    @Test
    void editProductPhoto(){
    }

    //golden path test
    @Test
    void deleteProductPhoto(){
    }






}
