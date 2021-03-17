package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByUserId(Integer userId);
    List<Question> findByItemFamilyId(Integer itemFamilyId);

    @Modifying
    @Query(
            value = "truncate questions restart identity cascade",
            nativeQuery = true
    )
    void reset();
}
