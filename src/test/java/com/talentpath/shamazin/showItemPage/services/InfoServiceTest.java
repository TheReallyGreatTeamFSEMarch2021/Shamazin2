package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.models.Info;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)



class InfoServiceTest {

    @Autowired
    InfoService infoServe;
    @Autowired
    ItemFamilyService itemFamilyServe;
    @Autowired
    ItemService itemServe;

    @BeforeEach
    void deleteAllItems() {
        itemFamilyServe.truncateItemFamily();
    }

    @Test
    void getAllInfo() {
        Item item = new Item();
        item.setName("Jesse");
        itemServe.addItem(item);
        Info info = new Info();
        info.setItem(item);
        infoServe.addInfo(info);
        Info info2 = new Info();
        infoServe.addInfo(info2);
        List<Info> infos = infoServe.getAllInfo();
        assertEquals(2, infos.size());
    }

    @Test
    void addInfo() {
        Item item = new Item();
        item.setName("Jesse");
        itemServe.addItem(item);
        Info info = new Info();
        info.setItem(item);
        info.setId(1);
        info.setValue("Hello");
        infoServe.addInfo(info);
    }
    @Test
    void getAllByItem() {
        Item item = new Item();
        item.setName("Jesse");
        Integer testId = itemServe.addItem(item).getId();
        Info info = new Info();
        info.setValue("Hello");
        info.setItem(item);
        infoServe.addInfo(info);
        Item item2 = new Item();
        item2.setName("Other");
        Integer id2 = itemServe.addItem(item2).getId();
        Info info2 = new Info();
        info2.setValue("Goodbye");
        info2.setItem(item2);
        infoServe.addInfo(info2);
        List<Info> infos = infoServe.getAllByItem(testId);
        assertEquals(1,infos.size());
        assertEquals("Hello",infos.get(0).getValue());
        List<Info> infos2 = infoServe.getAllByItem(id2);
        assertEquals(1,infos2.size());
        assertEquals("Goodbye",infos2.get(0).getValue());
    }

    @Test
    void deleteInfo() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void editInfo() {
    }
}