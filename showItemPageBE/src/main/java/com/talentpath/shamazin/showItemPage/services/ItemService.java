package com.talentpath.shamazin.showItemPage.services;



import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemDao;

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Item findByItemID(Integer itemID) throws NoSuchItemException {
        Optional<Item> item =itemDao.findById(itemID);
        if(item.isPresent()) return item.get();
        else throw new NoSuchItemException("No item with id: " + itemID);
    }

}
