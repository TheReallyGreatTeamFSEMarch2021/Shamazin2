package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.Review;
import com.talentpath.shamazin.showItemPage.models.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto,Integer> {

    List<ReviewPhoto> findAllByItemFamilyId(Integer itemFamilyId);

}
