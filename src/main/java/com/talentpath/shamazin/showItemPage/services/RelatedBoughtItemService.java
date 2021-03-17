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

    public Set<Item> getAllRelatedBoughtItems(Integer itemId) {
        Set<Item> relatedBoughtItems = new HashSet<>();
        Optional<Item> boughtItem = itemRepo.findById(itemId);

        if (boughtItem.isEmpty()) { return relatedBoughtItems; }

        Item item = boughtItem.get();
        relatedBoughtItems.addAll(item.getRelatedBoughtItems());
        relatedBoughtItems.addAll(item.getRelatedItems());

        return relatedBoughtItems;
    }

    public List<Item> getAllRelatedBoughtItemsByPage(Integer itemId, Integer page) {
        List<Item> relatedBoughtItems = new ArrayList<>(getAllRelatedBoughtItems(itemId));
        relatedBoughtItems.sort(Comparator.comparingInt(Item::getId));
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
