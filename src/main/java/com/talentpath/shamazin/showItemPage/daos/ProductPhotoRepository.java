package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, Integer> {
    Optional<List<ProductPhoto>> findByItem(Item item);
    Optional<ProductPhoto> findById(Integer productID);

}
