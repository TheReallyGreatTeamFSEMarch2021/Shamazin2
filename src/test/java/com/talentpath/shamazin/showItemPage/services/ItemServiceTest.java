package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.AutoConfigureDataCassandra;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)

class ItemServiceTest {

    @Autowired
    ItemService itemServe;//the service we are testing

    @Autowired
    ItemFamilyService itemFamilyServe;//this is included to reset the tables before tests

    @Autowired
    ItemFamilyRepository itemFamilyRepo;//this is to add families for testing.

    @Autowired
    ItemRepository itemRepo;//this is for testing, to get/add items without using service layer methods.

    @BeforeEach
    void deleteAllItems() {
        itemFamilyServe.truncateItemFamily();
    }

    @Test
    @Transactional
    void getAllItems() {
        Item item = new Item();
        item.setName("Testing get all");
        itemServe.addItem(item);
        List<Item> items = itemServe.getAllItems();
        assertEquals("Testing get all", items.get(0).getName());
        int i = 0;
    }

    @Test
    void addItem() {
        Item item = new Item();
        item.setId(1);
        item.setName("Jesse test");
        Integer id = itemServe.addItem(item).getId();
        Item addedItem = null;
        try {
            addedItem = itemServe.getItem(id);
            assertEquals("Jesse test",addedItem.getName());
        } catch (NoSuchItemException e) {
            fail("Exception caught during golden path test");
        }
    }

    @Test
    void practiceTest() {

    }


    @Test
    @Transactional
    void testGetAllItems() {
        ItemFamily doll = new ItemFamily("Doll XPS 13",null,null,null,"Doll");
        ItemFamily shamazinBasics = new ItemFamily("ShamazinBasics T-Shirt",null,null,null,"ShamazinBasics");

        Item dollxps13 = new Item(doll,null,null,"Doll XPS 13 7450",1300D,50,true);
        Item shamazinBasicsRedShirt = new Item(shamazinBasics,null,null,"Shamazin Basics T-Shirt (Red)",15D,200,true);

        itemFamilyRepo.saveAndFlush(doll);
        itemFamilyRepo.saveAndFlush(shamazinBasics);

        itemRepo.saveAndFlush(dollxps13);
        itemRepo.saveAndFlush(shamazinBasicsRedShirt);

        List<Item> testItems = itemServe.getAllItems();

        assertEquals(dollxps13,testItems.get(0));
        assertEquals(shamazinBasicsRedShirt,testItems.get(1));
    }

    @Test
    @Transactional
    void testAddItem() {
        ItemFamily hyperY = new ItemFamily("HyperY Cloud IX",null,null,null,"HyperY");
        itemFamilyRepo.saveAndFlush(hyperY);
        Item hyperYCloudIX = new Item(hyperY,null,null,"HyperY Cloud IX (Red)",100D,100,true);
        itemServe.addItem(hyperYCloudIX);

        Item testItem = itemRepo.findById(1).isPresent()?itemRepo.findById(1).get():null;
        assertEquals(hyperYCloudIX,testItem);
    }

    @Test
    void deleteItem() {
        ItemFamily hyperY = new ItemFamily("HyperY Cloud IX",null,null,null,"HyperY");
        itemFamilyRepo.saveAndFlush(hyperY);
        Item hyperYCloudIX = new Item(hyperY,null,null,"HyperY Cloud IX (Red)",100D,100,true);
        itemServe.addItem(hyperYCloudIX);

        itemServe.deleteItem(1);

        List<Item> items = itemRepo.findAll();

        assertEquals(0,items.size());
    }

    @Test
    @Transactional
    void getItem() {
        ItemFamily hyperY = new ItemFamily("HyperY Cloud IX",null,null,null,"HyperY");
        itemFamilyRepo.saveAndFlush(hyperY);
        Item hyperYCloudIX = new Item(hyperY,null,null,"HyperY Cloud IX (Red)",100D,100,true);
        itemServe.addItem(hyperYCloudIX);

        try {
            Item item = itemServe.getItem(1);
            assertEquals(hyperYCloudIX,item);
        } catch (NoSuchItemException e) {
            fail("Exception caught during golden path test: " + e.getMessage());
        }

    }

    @Test
    @Transactional
    void editItem() {
        ItemFamily hyperY = new ItemFamily("HyperY Cloud IX",null,null,null,"HyperY");
        itemFamilyRepo.saveAndFlush(hyperY);
        Item hyperYCloudIX = new Item(hyperY,null,null,"HyperY Cloud IX (Red)",100D,100,true);
        itemServe.addItem(hyperYCloudIX);

        hyperYCloudIX.setPrice(120D);
        hyperYCloudIX.setPrimeEligible(false);

        itemServe.editItem(hyperYCloudIX,1);

        try {
            Item item = itemServe.getItem(1);
            assertEquals(hyperYCloudIX,item);
        } catch (NoSuchItemException e) {
            fail("Exception caught during golden path test: " + e.getMessage());
        }
    }

    @Test
    @Transactional
    void getByFamilyId() {
        ItemFamily doll = new ItemFamily("Doll XPS 13",null,null,null,"Doll");
        ItemFamily shamazinBasics = new ItemFamily("ShamazinBasics T-Shirt",null,null,null,"ShamazinBasics");

        Item dollxps13 = new Item(doll,null,null,"Doll XPS 13 7450",1300D,50,true);
        Item shamazinBasicsRedShirt = new Item(shamazinBasics,null,null,"Shamazin Basics T-Shirt (Red)",15D,200,true);

        itemFamilyRepo.saveAndFlush(doll);
        itemFamilyRepo.saveAndFlush(shamazinBasics);

        itemRepo.saveAndFlush(dollxps13);
        itemRepo.saveAndFlush(shamazinBasicsRedShirt);

        List<Item> dollList = itemServe.findByFamilyId(1);
        List<Item> shamazinList = itemServe.findByFamilyId(2);

        assertEquals(dollxps13,dollList.get(0));
        assertEquals(shamazinBasicsRedShirt,shamazinList.get(0));
    }
}