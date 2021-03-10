package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFamilyRepository extends JpaRepository<ItemFamily,Integer> {

}
