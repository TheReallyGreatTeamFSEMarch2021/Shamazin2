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
            value = "truncate item_families restart identity cascade",
            nativeQuery = true
    )
    void truncateItem_Family();

    @Query(
        value = "select * from item_families i where i.id = some (select item_family_id from items t where t.id = ?1)",
            nativeQuery = true
    )
    ItemFamily findByItemId(Integer itemId);
}
