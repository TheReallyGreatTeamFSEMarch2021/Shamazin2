package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemFamilyRepository extends JpaRepository<ItemFamily,Integer> {

    @Modifying
    @Query(
<<<<<<< HEAD
            value = "truncate item_families restart identity cascade",
=======
            value = "truncate item_families restart identity",
>>>>>>> working through reset method for unit testing
            nativeQuery = true
    )
    void truncateItem_Family();
}
