package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.AnswerRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NullAnswerException;
import com.talentpath.shamazin.showItemPage.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository repo;

    public List<Answer> getAllAnswers(){return repo.findAll();}

    public Answer getAnswerById(Integer aId)throws NullAnswerException{
        Optional<Answer> answer = repo.findById(aId);
        if(answer.isPresent()){
            return answer.get();
        }else{
            throw new NullAnswerException("No answer found for id: "+ aId);
        }

    }

    public Answer addAnswer(Answer toAdd){return repo.saveAndFlush(toAdd);}

    public Answer editAnswer(Answer edited){return repo.saveAndFlush(edited);}

    public void deleteAnswerById(Integer aId){repo.deleteById(aId);}
}
