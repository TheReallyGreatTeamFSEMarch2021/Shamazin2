package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemFamilyException;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemFamilyService {

    @Autowired
    ItemFamilyRepository dao;

    public List<ItemFamily> getAllItemFamilies() {
        return dao.findAll();
    }

    @Transactional
    public void truncateItemFamily() {
        dao.truncateItem_Family();
    }


    public ItemFamily getItemFamilyById(Integer id) throws NoSuchItemFamilyException {
        Optional<ItemFamily> family = dao.findById(id);
        if(family.isPresent()) return family.get();
        else throw new NoSuchItemFamilyException("No item family with id: " + id);
    }

    public ItemFamily addItemFamily(ItemFamily itemFamily){
        return dao.saveAndFlush(itemFamily);
    }

    public ItemFamily getItemFamilyByItemId(Integer itemId) {
        return dao.findByItemId(itemId);
    }
}
