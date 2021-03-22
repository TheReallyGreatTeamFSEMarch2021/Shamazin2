package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.InfoRepository;
import com.talentpath.shamazin.showItemPage.daos.ItemRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchInfoException;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.models.Info;
import com.talentpath.shamazin.showItemPage.models.Item;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoService {
    @Autowired
    InfoRepository infoDao;

    public List<Info> getAllInfo() {
        return infoDao.findAll();
    }

    public Info addInfo(Info info) {
        return infoDao.saveAndFlush(info);
    }

    public void deleteInfo(Integer id) {
        infoDao.deleteById(id);
    }

    public Info getInfo(Integer id) throws NoSuchInfoException {
        Optional<Info> info = infoDao.findById(id);
        if(info.isPresent()) return info.get();
        else throw new NoSuchInfoException("No item with id: " + id);
    }

    public Info editInfo(Info info, Integer id) {
        Info current = infoDao.getOne(id);
        BeanUtils.copyProperties(info,current,"id");
        return infoDao.saveAndFlush(current);
    }
    public List<Info> getAllByItem(Integer id) {
        return infoDao.findByitemId(id);
    }


}
