package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.QuestionRepository;
import com.talentpath.shamazin.showItemPage.models.Answer;
import com.talentpath.shamazin.showItemPage.models.ItemFamily;
import com.talentpath.shamazin.showItemPage.models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class QuestionTest {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    ItemFamilyService itemFamilyService;

    @Autowired
    ItemFamilyRepository itemFamilyRepo;

    @BeforeEach
    void deleteAllItems() {
       itemFamilyService.truncateItemFamily();
    }

    @Test
    @Transactional
    void getAllQuestions(){
        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");
        ItemFamily family2 = new ItemFamily("family2",null,null,null,"brand2");

        Question question1 = new Question(1,1,1,"QuestionTest", 10,null, family1, null);
        Question question2 = new Question(2,2,2,"Question",99,null,family2, null);

        itemFamilyRepo.saveAndFlush(family1);
        itemFamilyRepo.saveAndFlush(family2);
        questionRepo.saveAndFlush(question1);
        questionRepo.saveAndFlush(question2);

        List<Question> questions = questionService.getAllQuestions();
        assertEquals(2, questions.size());
        assertEquals(question1,questions.get(0));
        assertEquals(question2, questions.get(1));

    }

    @Test
    @Transactional
    void getByItemFamily(){
        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");
        ItemFamily family2 = new ItemFamily("family2",null,null,null,"brand2");

        Question question1 = new Question(1,1,1,"QuestionTest", 10,null, family1, null);
        Question question2 = new Question(2,2,2,"Question",99,null,family2, null);
        Question question3 = new Question(3,2,3,"Second question for item #2", -5, null, family2, null);

        itemFamilyRepo.saveAndFlush(family1);
        itemFamilyRepo.saveAndFlush(family2);
        questionRepo.saveAndFlush(question1);
        questionRepo.saveAndFlush(question2);
        questionRepo.saveAndFlush(question3);

        List<Question> questions = questionService.getByItemFamilyId(1);
        assertEquals(1, questions.size());
        assertEquals(question1, questions.get(0));


        List<Question> questionz = questionService.getByItemFamilyId(2);
        assertEquals(2, questionz.size());
        assertEquals(question2, questionz.get(0));
        assertEquals(question3,questionz.get(1));

    }

    @Test
    @Transactional
    void getById(){

        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");

        Question question1 = new Question(1,1,1,"QuestionTest", 10,null, family1, null);
        Question question2 = new Question(2,2,2,"Question",99,null,family1, null);

        itemFamilyRepo.saveAndFlush(family1);
        questionRepo.saveAndFlush(question1);
        questionRepo.saveAndFlush(question2);
        
    }
}
