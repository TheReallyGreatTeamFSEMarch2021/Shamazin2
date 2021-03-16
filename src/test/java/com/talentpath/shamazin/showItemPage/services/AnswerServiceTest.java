package com.talentpath.shamazin.showItemPage.services;

import com.talentpath.shamazin.showItemPage.daos.AnswerRepository;
import com.talentpath.shamazin.showItemPage.daos.ItemFamilyRepository;
import com.talentpath.shamazin.showItemPage.daos.QuestionRepository;
import com.talentpath.shamazin.showItemPage.exceptions.NullAnswerException;
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

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AnswerServiceTest {

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerRepository answerRepo;

    @Autowired
    AnswerService answerService;

    @Autowired
    ItemFamilyRepository itemFamilyRepo;

    @Autowired
    ItemFamilyService itemFamilyService;

    @BeforeEach
    void deleteAllItems(){itemFamilyService.truncateItemFamily();}

    @Test
    @Transactional
    void getAllAnswers(){

        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");
        ItemFamily family2 = new ItemFamily("family2",null,null,null,"brand2");

        Question question1 = new Question(1,1,1,"QuestionTest", 10,null, family1, null);
        Question question2 = new Question(2,2,2,"Question",99,null,family2, null);

        Answer answer1 = new Answer(1,1,"Brilliant answer", null, question1);
        Answer answer2 = new Answer(2,2,"Even better answer", null,question1);

        itemFamilyRepo.saveAndFlush(family1);
        itemFamilyRepo.saveAndFlush(family2);
        questionRepo.saveAndFlush(question1);
        questionRepo.saveAndFlush(question2);
        answerRepo.saveAndFlush(answer1);
        answerRepo.saveAndFlush(answer2);

        List<Answer> answers = answerService.getAllAnswers();

        assertEquals(2, answers.size());
        assertEquals(answer1, answers.get(0));
        assertEquals(answer2, answers.get(1));

    }

    @Test
    @Transactional
    void getAnswerById(){
        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");
        Question question1 = new Question(1,1,1,"QuestionTest", 10,null, family1, null);

        Answer answer1 = new Answer(1,1,"Brilliant answer", null, question1);
        Answer answer2 = new Answer(2,2,"Even better answer", null,question1);

        itemFamilyRepo.saveAndFlush(family1);
        questionRepo.saveAndFlush(question1);
        answerRepo.saveAndFlush(answer1);
        answerRepo.saveAndFlush(answer2);

        try{
            Answer ans = answerService.getAnswerById(1);
            assertEquals(ans, answer1);
        }catch(NullAnswerException e){
            fail("Caught exception while trying to receive answer with getAnswerById");
        }
    }

    @Test
    @Transactional
    void getAnswerByQuestion(){

    }

}
