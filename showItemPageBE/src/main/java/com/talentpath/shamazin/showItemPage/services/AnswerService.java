package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.AnswerRepository;
import com.talentpath.shamazin.showItemPage.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository repo;

    public List<Answer> getAllAnswers(){return repo.findAll();}

    public Answer getAnswerById(Integer aId){return repo.findById(aId).get();}

    public Answer addAnswer(Answer toAdd){return repo.saveAndFlush(toAdd);}

    public Answer editAnswer(Answer edited){return repo.saveAndFlush(edited);}

    public void deleteAnswer(Integer aId){repo.deleteById(aId);}
}
