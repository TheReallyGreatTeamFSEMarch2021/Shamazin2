package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.ReviewRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NullReviewException;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ReviewServiceTest {

    @Autowired
    ReviewService service; //service to test

    @Autowired
    ReviewRepository reviewRepo; //for adding/getting reviews

    @Autowired
    ItemFamilyService itemFamilyService; //for resetting tables

    @Autowired
    ItemFamilyRepository itemFamilyRepo; //for adding item families for testing


    @BeforeEach
    void setUp() {
        itemFamilyService.truncateItemFamily();
    }

    @Test
    @Transactional
    void getAllReviews() {
        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");
        ItemFamily family2 = new ItemFamily("family2",null,null,null,"brand2");

        Review review1 = new Review(family1, "title1", "content1", 1, 1);
        Review review2 = new Review(family2, "title2", "content2", 2, 2);

        itemFamilyRepo.saveAndFlush(family1);
        itemFamilyRepo.saveAndFlush(family2);
        reviewRepo.saveAndFlush(review1);
        reviewRepo.saveAndFlush(review2);

        List<Review> allReviews = service.getAllReviews();

        assertEquals(2, allReviews.size());
        assertEquals(review1, allReviews.get(0));
        assertEquals(review2, allReviews.get(1));

    }

    @Test
    @Transactional
    void getByItemFamily() {
        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");
        ItemFamily family2 = new ItemFamily("family2",null,null,null,"brand2");

        Review review1 = new Review(family1, "title1", "content1", 1, 1);
        Review review2 = new Review(family1, "title2", "content2", 2, 2);
        Review review3 = new Review(family2, "title3", "content3", 3, 3);

        itemFamilyRepo.saveAndFlush(family1);
        itemFamilyRepo.saveAndFlush(family2);
        reviewRepo.saveAndFlush(review1);
        reviewRepo.saveAndFlush(review2);
        reviewRepo.saveAndFlush(review3);

        List<Review> reviews = service.getByItemFamily(1);

        assertEquals(2, reviews.size());
        assertEquals(review1, reviews.get(0));
        assertEquals(review2, reviews.get(1));
    }

    @Test
    @Transactional
    void getById()   {
        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");

        Review review1 = new Review(family1, "title1", "content1", 1, 1);
        Review review2 = new Review(family1, "title2", "content2", 2, 2);

        itemFamilyRepo.saveAndFlush(family1);
        reviewRepo.saveAndFlush(review1);
        reviewRepo.saveAndFlush(review2);

        try {
            Review r2 = service.getById(2);
            assertEquals(review2, r2);
        } catch (NullReviewException e) {
            fail("Unexpected error during ReviewService test: getById");
        }

    }
}