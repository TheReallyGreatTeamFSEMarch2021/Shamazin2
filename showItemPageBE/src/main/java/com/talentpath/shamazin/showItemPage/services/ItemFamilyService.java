package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


}
