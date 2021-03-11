package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.models.Review;
import com.talentpath.shamazin.showItemPage.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    ReviewService service;

    @GetMapping("/getAll")
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }

    @GetMapping("/getByItemFamily/{itemFamily}")
    public List<Review> getByItemFamily(@PathVariable Integer itemFamily){
        return service.getByItemFamily(itemFamily);
    }
}
