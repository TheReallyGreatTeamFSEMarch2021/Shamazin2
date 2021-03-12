package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RelatedBoughtItemService {

    @Autowired
    ItemRepository itemRepo;

    public Set<Item> getAllRelatedBoughtItems(Integer itemId) {
        Set<Item> relatedBoughtItems = new HashSet<>();
        Optional<Item> boughtItem = itemRepo.findById(itemId);

        if (boughtItem.isEmpty()) { return relatedBoughtItems; }

        Item item = boughtItem.get();
        relatedBoughtItems.addAll(item.getRelatedBoughtItems());
        relatedBoughtItems.addAll(item.getRelatedItems());

        return relatedBoughtItems;
    }

}
