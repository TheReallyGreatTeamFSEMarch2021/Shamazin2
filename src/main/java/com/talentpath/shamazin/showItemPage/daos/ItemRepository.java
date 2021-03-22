package com.talentpath.shamazin.showItemPage.daos;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import com.talentpath.shamazin.showItemPage.models.Item;
import com.talentpath.shamazin.showItemPage.models.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Item> findById(Integer itemID);

    List<Item> findAllByItemFamilyId(Integer ItemFamilyId);

    @Modifying
    @Query(
            value="truncate items restart identity cascade",
            nativeQuery = true
    )
    public void truncate_Item();


    @Query(
            value = "Select item_family_id from items where id = ?1",
            nativeQuery = true
    )
    Integer getFamilyId(Integer itemId);
}
