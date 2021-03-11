package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    List<Answer> findByUserId(Integer userId);

}
