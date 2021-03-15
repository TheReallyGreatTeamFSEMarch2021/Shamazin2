package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ProductPhotoRepository;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class productPhotoServiceTest {
    @Autowired
    ProductPhotoService productPhotoServ;

    @Autowired
    ItemService itemServ;

    @Autowired
    ProductPhotoRepository productPhotoRepo;

    @BeforeEach
    void deleteAllItems(){
        productPhotoServ.truncateProductPhotos();
    }

    //golden path test
    @Test
    void getAllProductPhotosByItemID(){
        Item item = new Item();
        item.setName("Blink Cameras Test");
        itemServ.addItem(item);

        ProductPhoto prodPhoto1 = new ProductPhoto("https://favrecipezaws.s3.amazonaws.com/BlinkMiniCameraPicR.jpg", item);
        ProductPhoto prodPhoto2 = new ProductPhoto("https://favrecipezaws.s3.amazonaws.com/twoBlinkCameras.jpeg",item);


    }




}
