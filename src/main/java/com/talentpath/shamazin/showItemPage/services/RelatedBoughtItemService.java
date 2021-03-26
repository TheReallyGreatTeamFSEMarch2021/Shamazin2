package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RelatedBoughtItemService {

    @Autowired
    ItemRepository itemRepo;

    public List<Item> getAllRelatedBoughtItems(Integer itemId) {
        List<Item> relatedBoughtItems = new ArrayList<>();
        Optional<Item> boughtItem = itemRepo.findById(itemId);

        if (boughtItem.isEmpty()) { return relatedBoughtItems; }

        Set<Item> relatedBoughtItemsSet = new HashSet<>();
        Item item = boughtItem.get();
        relatedBoughtItemsSet.addAll(item.getRelatedBoughtItems());
        relatedBoughtItemsSet.addAll(item.getRelatedItems());

        relatedBoughtItems.addAll(relatedBoughtItemsSet);
        relatedBoughtItems.sort(Comparator.comparingInt(Item::getId));
        return relatedBoughtItems;
    }

    public List<Item> getAllRelatedBoughtItemsByPage(Integer itemId, Integer page) {
        List<Item> relatedBoughtItems = getAllRelatedBoughtItems(itemId);
        Integer total = relatedBoughtItems.size();

        if (total <= 7) {
            return relatedBoughtItems;
        }

        Integer start = (page - 1) * 7;
        Integer end = start + 7;

        return end > total ? relatedBoughtItems.subList(start, total) : relatedBoughtItems.subList(start, end);
    }

    public void deleteAllRelatedBoughtItems(Integer itemId) {
        Optional<Item> boughtItem = itemRepo.findById(itemId);

        if (boughtItem.isPresent()) {
            Item item = boughtItem.get();
            Set<Item> relatedItems = item.getRelatedItems();

            for (Item relatedItem : relatedItems) {
                relatedItem.getRelatedBoughtItems().remove(item);
            }

            Set<Item> relatedBoughtItems = item.getRelatedBoughtItems();
            relatedBoughtItems.removeAll(relatedBoughtItems);

            itemRepo.flush();
        }

    }

}
