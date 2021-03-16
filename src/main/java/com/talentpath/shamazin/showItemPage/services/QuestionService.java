package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.QuestionRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NullQuestionException;
import com.talentpath.shamazin.showItemPage.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionDao;

    public List<Question> getAllQuestions() {return questionDao.findAll();}

    public List<Question> getByItemFamilyId(Integer itemFamilyId){return questionDao.findByItemFamilyId(itemFamilyId);}


    public Question getQuestionById(Integer qId) throws NullQuestionException {
        Optional<Question> question = questionDao.findById(qId);

        if(question.isPresent()){
            return question.get();
        }else{
            throw new NullQuestionException("No question found with id: " + qId);
        }
    }

    public Question addQuestion(Question toAdd){return questionDao.saveAndFlush(toAdd);}

    public Question editQuestion(Question edited){return questionDao.saveAndFlush(edited);}

    public void deleteQuestion(Integer qId){questionDao.deleteById(qId);}

    @Transactional
    public void truncateQuestionList() {
        questionDao.reset();
    }

    /*
    public Review getById(Integer id) throws NullReviewException {
        Optional<Review> review  = reviewDao.findById(id);

        //check if review exists, if not throw exception
        if(review.isPresent()){
            return review.get();
        }else{
            throw new NullReviewException("No review found with id: "+id);
        }
    }
     */
}
