package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ReviewRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NullReviewException;
import com.talentpath.shamazin.showItemPage.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewDao;

    public List<Review> getAllReviews() {return reviewDao.findAll();}

    public List<Review> getByItemFamily(Integer itemFamilyId) {
        return reviewDao.findByItemFamilyId(itemFamilyId);
    }

    public Review getById(Integer id) throws NullReviewException {
        Optional<Review> review  = reviewDao.findById(id);

        //check if review exists, if not throw exception
        if(review.isPresent()){
            return review.get();
        }else{
            throw new NullReviewException("No review found with id: "+id);
        }
    }
}
