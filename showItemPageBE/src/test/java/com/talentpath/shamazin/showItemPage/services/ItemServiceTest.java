package com.talentpath.shamazin.showItemPage.services;

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
    ItemService itemServe;
    ItemFamilyService itemFamilyServe;

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
        Item addedItem = itemServe.getItem(id);
        assertEquals("Jesse test",addedItem.getName());
    }


}