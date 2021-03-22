package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ProductPhotoRepository;
import com.talentpath.shamazin.showItemPage.exceptions.InvalidIDException;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.exceptions.NullItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullProductPhotoException;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.NoSuchElementException;

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
    void deletePrevAddNewData() throws NullArgumentException {
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
            List<ProductPhoto> productPhotos = productPhotoServ.getProductPhotosByItemId(1);
            assertEquals(0, productPhotos.size());

            //Assert
            //Adding a ProductPhoto, size should increase
            ProductPhoto productPhotoToAdd = new ProductPhoto("photo1URL", item);
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            productPhotos = productPhotoServ.getProductPhotosByItemId(1);
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
    void getProductPhotosByItemId() throws NullArgumentException {
        ItemFamily toAddItemFamily = new ItemFamily();
        toAddItemFamily.setFamilyName("Security Cameras");
        toAddItemFamily.setBrand("Blink");
        ItemFamily itemFamily1 = itemFamServ.addItemFamily(toAddItemFamily);

        Item item = new Item();
        item.setItemFamily(itemFamily1);
        item.setName("Blink Cameras Test");
        item.setPrice(39.99);
        item.setStockRemaining(29);
        item.setPrimeEligible(true);

        Item itemAdded = itemServ.addItem(item);
        try{
            List<ProductPhoto> productPhotos = productPhotoServ.getProductPhotosByItemId(3);
            assertEquals(0, productPhotos.size());

            ProductPhoto prodPhoto1 = new ProductPhoto("https://favrecipezaws.s3.amazonaws.com/BlinkMiniCameraPicR.jpg", itemAdded);
            ProductPhoto prodPhoto2 = new ProductPhoto("https://favrecipezaws.s3.amazonaws.com/twoBlinkCameras.jpeg",itemAdded);
            productPhotoRepo.saveAndFlush(prodPhoto1);
            productPhotoRepo.saveAndFlush(prodPhoto2);

            productPhotos = productPhotoServ.getProductPhotosByItemId(3);
            ProductPhoto productPhoto1 = productPhotos.get(0);
            ProductPhoto productPhoto2 = productPhotos.get(1);

            assertEquals(1, prodPhoto1.getId());
            assertEquals(2, prodPhoto2.getId());
            assertEquals("https://favrecipezaws.s3.amazonaws.com/BlinkMiniCameraPicR.jpg", prodPhoto1.getPhotoURL());
            assertEquals("https://favrecipezaws.s3.amazonaws.com/twoBlinkCameras.jpeg", prodPhoto2.getPhotoURL());

        }catch(Exception ex){
            fail("Golden path test for getProductPhotosByItsItem. Should not have triggered any exception. " + ex.getMessage());
        }
    }

    @Test
    void getProductPhotosByItemIdNullId() throws NullItemException {
        Item item = null;
        try {
            List<ProductPhoto> productPhotos = productPhotoServ.getProductPhotosByItemId(null);
            fail("No exception thrown.");

        }
        catch (NullArgumentException ignored) {

        }
        catch (Exception ex) {
            fail("In getProductPhotosByItsItemNullItem, should not run into another exception besides NullItemException. " + ex);
        }
    }

    //golden path test
    @Test
    @Transactional
    void getProductPhotoByID() throws NullProductPhotoException, NullArgumentException {
        //Arrange
        //Act
        List<Item> items = itemServ.getAllItems();
        Item item = items.get(0);
        Item item2 = items.get(1);

        try {
            ProductPhoto productPhotoToAdd = new ProductPhoto("photo1URL", item);
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            ProductPhoto productPhotoToAdd2 = new ProductPhoto("photo2URL", item2);
            productPhotoServ.addProductPhoto(productPhotoToAdd2);
            ProductPhoto grabbedProductPhoto = productPhotoServ.getProductPhotoByID(1);
            assertEquals("photo1URL", grabbedProductPhoto.getPhotoURL());
            assertEquals("Blue Addidas T-Shirt", grabbedProductPhoto.getItem().getName());
            assertEquals(20.75, grabbedProductPhoto.getItem().getPrice());

            grabbedProductPhoto = productPhotoServ.getProductPhotoByID(2);
            assertEquals("photo2URL", grabbedProductPhoto.getPhotoURL());
            assertEquals("Green Addidas T-Shirt", grabbedProductPhoto.getItem().getName());
            assertEquals(22.75, grabbedProductPhoto.getItem().getPrice());
        }catch(Exception ex){
            fail("Golden path test for getProductPhotoByID. Should not have triggered any exception. " + ex.getMessage());
        }
    }



    //should throw error when invalid ID (0 or negative)
    @Test
    void getProductPhotoByInvalidID() throws InvalidIDException, NullArgumentException {
        //Arrange, Act, Assert
        try {
            ProductPhoto grabbedProductPhoto = productPhotoServ.getProductPhotoByID(-1);
            fail("In getProductPhotoByInvalidID, tried to grab productPhoto with ID = -1. Should have thrown InvalidIDException.");
        }catch(InvalidIDException ex){
            System.out.println("Correctly threw InvalidIDException. Tried to grab productPhoto with ID = -1. " + ex.getMessage());
        }catch(Exception ex){
            fail("In getProductPhotoByInvalidID, should not run into another exception besides InvalidIDException. " + ex);

        }
    }



    //should throw an error when photoID doesn't exist in system
    @Test
    void getProductPhotoByNonExistentID() throws NoSuchElementException {
        //Arrange, Act, Assert
        try {
            ProductPhoto grabbedProductPhoto = productPhotoServ.getProductPhotoByID(1000);
            fail("In getProductPhotoByNonExistentID, tried to grab productPhoto with ID=1000. Should have thrown NoSuchElementException.");
        }catch(NoSuchElementException | InvalidIDException ex){
            System.out.println("Correctly threw NoSuchElementException. There is no product photo with ID=1000. " + ex.getMessage());
        }catch(Exception ex){
            fail("In getProductPhotoByNonExistentID, should not run into another exception besides NoSuchElementException. " + ex);
        }
    }

    //golden path test
    @Test
    @Transactional
    void editProductPhoto(){
        List<Item> items = itemServ.getAllItems();
        Item item = items.get(0);
        Item item2 = items.get(1);

        try {
            ProductPhoto productPhotoToAdd = new ProductPhoto("photo1URL.com", item);
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            ProductPhoto prodPhoto = productPhotoServ.getProductPhotoByID(1);
            assertEquals("photo1URL.com", prodPhoto.getPhotoURL());
            assertEquals(1, prodPhoto.getId());
            assertEquals("Blue Addidas T-Shirt", prodPhoto.getItem().getName());
            prodPhoto.setPhotoURL("editedPhoto1URL.com");
            prodPhoto.setItem(item2);
            productPhotoServ.editProductPhoto(prodPhoto);
            prodPhoto = productPhotoServ.getProductPhotoByID(1);
            assertEquals("editedPhoto1URL.com", prodPhoto.getPhotoURL());
            assertEquals(1, prodPhoto.getId());
            assertEquals("Green Addidas T-Shirt", prodPhoto.getItem().getName());
        }catch(Exception ex){
            fail("Golden path test for editProductPhoto. Should not have triggered any exception. " + ex.getMessage());

        }
    }

    //golden path test
    @Test
    void deleteProductPhoto(){
        List<Item> items = itemServ.getAllItems();
        Item item = items.get(0);
        Item item2 = items.get(1);

        try {
            ProductPhoto productPhotoToAdd = new ProductPhoto("photo1URL", item);
            productPhotoServ.addProductPhoto(productPhotoToAdd);
            ProductPhoto productPhotoToAdd2 = new ProductPhoto("photo2URL", item);
            productPhotoServ.addProductPhoto(productPhotoToAdd2);
            List<ProductPhoto> productPhotos = productPhotoServ.getProductPhotosByItemId(1);
            assertEquals(2, productPhotos.size());
            productPhotoServ.deleteProductPhoto(1);
            productPhotos = productPhotoServ.getProductPhotosByItemId(1);
            assertEquals(1, productPhotos.size());

        }catch(Exception ex){
            fail("Golden path test for deleteProductPhoto. Should not have triggered any exception. " + ex.getMessage());
        }
    }
}
