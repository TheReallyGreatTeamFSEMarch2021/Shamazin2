package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.AutoConfigureDataCassandra;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)

class ItemServiceTest {

    @Autowired
    ItemService itemServe;
    @Autowired
    ItemRepository itemDao;
    @Autowired
    ItemFamilyRepository itemFamilyDao;
    @Autowired
    ItemFamilyService itemFamilyService;
    @BeforeEach
    void setUp() {
        itemFamilyService.truncateItemFamily();
    }

    @Test
    @Transactional
    void getAllItems() {
        ItemFamily family = new ItemFamily("Doll XPS 13 Laptop",null,null,null,"Doll");
        Item item = new Item(family,null,"Doll XPS 13 7480",1300D,50,true);
        Item item1 = new Item(family,null,"Doll XPS 13 7780",2000D,10,true);
        itemFamilyDao.saveAndFlush(family);
        itemDao.saveAndFlush(item);
        itemDao.saveAndFlush(item1);
        List<Item> items = itemServe.getAllItems();
        Item item2 = items.get(0);
        Item item3 = items.get(1);
        assertEquals(item,item2);
        assertEquals(item1,item3);
        assertNotEquals(item,item3);
        assertNotEquals(item1,item2);

    }
}