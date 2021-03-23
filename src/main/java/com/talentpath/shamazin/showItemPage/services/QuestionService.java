package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.QuestionRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemFamilyException;
import com.talentpath.shamazin.showItemPage.exceptions.NullQuestionException;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
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

    @Autowired
    ItemFamilyRepository itemFamilyRepo;

    public List<Question> getAllQuestions() {return questionDao.findAll();}

    public List<Question> getByItemFamilyId(Integer itemFamilyId) throws NoSuchItemFamilyException {
        Optional<ItemFamily> itemFamily = itemFamilyRepo.findById(itemFamilyId);
        if(itemFamily.isPresent()) return questionDao.findByItemFamilyId(itemFamilyId);
        else throw new NoSuchItemFamilyException("No Item Family with id: " + itemFamilyId);
    }

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
}
