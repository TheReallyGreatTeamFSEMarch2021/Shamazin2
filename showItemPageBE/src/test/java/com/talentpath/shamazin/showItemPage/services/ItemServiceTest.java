package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.AutoConfigureDataCassandra;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    void getAllItems() {
        itemServe.getAllItems();
    }
}