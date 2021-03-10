package com.talentpath.shamazin.showItemPage.services;


import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemDao;

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }
}
