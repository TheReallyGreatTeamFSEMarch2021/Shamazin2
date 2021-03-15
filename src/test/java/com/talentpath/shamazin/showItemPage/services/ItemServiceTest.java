package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        try {
            Item item = new Item();
            item.setName("Testing get all");
            itemServe.addItem(item);
            List<Item> items = itemServe.getAllItems();
            assertEquals("Testing get all", items.get(0).getName());
        }
        catch(Exception e) {
            fail("Exception caught during golden path test: " + e.getMessage());
        }

    }
    @Test
    void addItem() {
        try {
            Item item = new Item();
            item.setId(1);
            item.setName("Jesse test");
            Integer id = itemServe.addItem(item).getId();
            Item addedItem = null;
            addedItem = itemServe.getItem(id);
            assertEquals("Jesse test",addedItem.getName());
        } catch (Exception e) {
            fail("Exception caught during golden path test");
        }

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
        try {
            ItemFamily hyperY = new ItemFamily("HyperY Cloud IX", null, null, null, "HyperY");
            itemFamilyRepo.saveAndFlush(hyperY);
            Item hyperYCloudIX = new Item(hyperY, null, null, "HyperY Cloud IX (Red)", 100D, 100, true);
            itemServe.addItem(hyperYCloudIX);

            Item testItem = itemRepo.findById(1).isPresent() ? itemRepo.findById(1).get() : null;
            assertEquals(hyperYCloudIX, testItem);
        }
        catch(Exception e) {
            fail("Exception caught during golden path test: " + e.getMessage());
        }
    }

    @Test
    @Transactional
    void testAddItemNullItem() {
        try {
            itemServe.addItem(null);
            fail("No exception caught");
        }
        catch(NullArgumentException ignored) {

        }
        catch (Exception e) {
            fail("Wrong exception caught: " + e.getMessage());
        }
    }

    @Test
    void deleteItem() {
        try {
            ItemFamily hyperY = new ItemFamily("HyperY Cloud IX", null, null, null, "HyperY");
            itemFamilyRepo.saveAndFlush(hyperY);
            Item hyperYCloudIX = new Item(hyperY, null, null, "HyperY Cloud IX (Red)", 100D, 100, true);
            itemServe.addItem(hyperYCloudIX);

            itemServe.deleteItem(1);

            List<Item> items = itemRepo.findAll();

            assertEquals(0, items.size());
        }
        catch(Exception e) {
            fail("Exception caught during golden path test: " + e.getMessage());
        }
    }

    @Test
    void deleteItemWrongId() {
        try {
            itemServe.deleteItem(4);
            fail("No exception caught.");
        }
        catch(NoSuchItemException ignored) {

        }
        catch(Exception e) {
            fail("Wrong exception caught: " + e.getClass() + " " + e.getMessage());
        }
    }

    @Test
    void deleteItemNullId() {
        try {
            itemServe.deleteItem(null);
            fail("No exception caught.");
        }
        catch(NullArgumentException ignored) {

        }
        catch(Exception e) {
            fail("Wrong exception caught: " + e.getClass() + " " + e.getMessage());
        }
    }

    @Test
    @Transactional
    void getItem() {
        try {
            ItemFamily hyperY = new ItemFamily("HyperY Cloud IX",null,null,null,"HyperY");
            itemFamilyRepo.saveAndFlush(hyperY);
            Item hyperYCloudIX = new Item(hyperY,null,null,"HyperY Cloud IX (Red)",100D,100,true);
            itemServe.addItem(hyperYCloudIX);
            Item item = itemServe.getItem(1);
            assertEquals(hyperYCloudIX,item);
        } catch (Exception e) {
            fail("Exception caught during golden path test: " + e.getMessage());
        }
    }

    @Test
    @Transactional
    void getItemWrongId() {
        try {
            itemServe.getItem(1);
            fail("No exception caught.");
        }
        catch(NoSuchItemException ignored) {

        }
        catch(Exception e) {
            fail("Wrong exception caught: " + e.getClass() + " " + e.getMessage());
        }
    }

    @Test
    @Transactional
    void editItem() {
        try {
            ItemFamily hyperY = new ItemFamily("HyperY Cloud IX",null,null,null,"HyperY");
            itemFamilyRepo.saveAndFlush(hyperY);
            Item hyperYCloudIX = new Item(hyperY,null,null,"HyperY Cloud IX (Red)",100D,100,true);
            itemServe.addItem(hyperYCloudIX);

            hyperYCloudIX.setPrice(120D);
            hyperYCloudIX.setPrimeEligible(false);

            itemServe.editItem(hyperYCloudIX,1);
            Item item = itemServe.getItem(1);
            assertEquals(hyperYCloudIX,item);
        } catch (Exception e) {
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