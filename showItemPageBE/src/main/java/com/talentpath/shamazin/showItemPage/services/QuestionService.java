package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.QuestionRepository;
import com.talentpath.shamazin.showItemPage.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionDao;

    public List<Question> getAllQuestions() {return questionDao.findAll();}

    public Question getQuestionById(Integer qId){
        return questionDao.findById(qId).get();
    }

    public Question addQuestion(Question toAdd){return questionDao.saveAndFlush(toAdd);}

    public Question editQuestion(Question edited){return questionDao.saveAndFlush(edited);}

    public void deleteQuestion(Integer qId){questionDao.deleteById(qId);}
}
