package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ReviewRepository;
import com.talentpath.shamazin.showItemPage.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewDao;

    public List<Review> getAllReviews() {return reviewDao.findAll();}

    public List<Review> getByItemFamily(Integer itemFamilyId) {
        return reviewDao.findByItemFamilyId(itemFamilyId);
    }
}
