package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    List<Answer> findByUserId(Integer userId);
    List<Answer> findByQuestionQuestionId(Integer questionId);

    @Modifying
    @Query(
            value = "truncate answers restart identity cascade",
            nativeQuery = true
    )
    void reset();


}


