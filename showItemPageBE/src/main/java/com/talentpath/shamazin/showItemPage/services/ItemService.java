package com.talentpath.shamazin.showItemPage.services;


import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemDao;

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Item addItem(Item item) {
        return itemDao.saveAndFlush(item);
    }

    public void deleteItem(Integer id) {
        itemDao.deleteById(id);
    }

    public Item getItem(Integer id) {

        return itemDao.findById(id).get();
    }

    public Item editItem(Item item, Integer id) {
        Item current = itemDao.getOne(id);
        BeanUtils.copyProperties(item,current,"id");
        return itemDao.saveAndFlush(current);
    }


}
