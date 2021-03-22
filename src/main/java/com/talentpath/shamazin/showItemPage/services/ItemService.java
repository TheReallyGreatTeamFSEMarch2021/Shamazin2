package com.talentpath.shamazin.showItemPage.services;


import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemDao;

    @Autowired
    RelatedBoughtItemService relatedBoughtItemService;

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Item addItem(Item item) throws NullArgumentException {
        if(item == null) throw new NullArgumentException("Item object passed to addItem in ItemService must not be null!");
        return itemDao.saveAndFlush(item);
    }

    public void deleteItem(Integer id) throws NoSuchItemException, NullArgumentException {
        if(id == null) throw new NullArgumentException("Id parameter passed to deleteItem in ItemService must not be null!");
        else if(!itemDao.existsById(id)) throw new NoSuchItemException("No Item with id: " + id + " exists!");
        relatedBoughtItemService.deleteAllRelatedBoughtItems(id);
        itemDao.deleteById(id);
    }

    public Item getItem(Integer id) throws NoSuchItemException, NullArgumentException {
        if(id == null) throw new NullArgumentException("Id parameter passed to getItem in ItemService must not be null!");
        Optional<Item> item = itemDao.findById(id);
        if(item.isPresent()) return item.get();
        else throw new NoSuchItemException("No item with id: " + id);
    }

    public Item editItem(Item item, Integer id) throws NoSuchItemException, NullArgumentException {
        if(item == null || id == null) throw new NullArgumentException("One or more null parameters passed to editItem in ItemService. ");
        else if(!itemDao.existsById(id)) throw new NoSuchItemException("No item with id: " + id);
        Item current = itemDao.getOne(id);
        BeanUtils.copyProperties(item,current,"id");
        return itemDao.saveAndFlush(current);
    }


    public List<Item> findByFamilyId(Integer familyId) {
        return itemDao.findAllByItemFamilyId(familyId);
    }

    public Integer getFamilyId(Integer itemId) throws NoSuchItemException, NullArgumentException {
        if(itemId==null) throw new NullArgumentException("itemId passed to getFamilyId in itemService must not be null!");
        Integer ans = itemDao.getFamilyId(itemId);
        if(ans==null) throw new NoSuchItemException("No item with id: " + itemId + " exists!");
        else return ans;
    }
}
