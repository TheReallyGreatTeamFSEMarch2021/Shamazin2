package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.Review;
import com.talentpath.shamazin.showItemPage.models.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto,Integer> {

    List<ReviewPhoto> findAllByItemFamilyId(Integer itemFamilyId);

}
