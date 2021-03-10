package com.talentpath.shamazin.showItemPage.daos;

import com.talentpath.shamazin.showItemPage.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    //List<Question> getAllQuestions(Integer userId);
    //Question getQuestionById(Integer questionId);
}
