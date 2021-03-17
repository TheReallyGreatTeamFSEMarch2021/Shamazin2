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
        assertEquals(2, answerService.getAllAnswers().size());

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

        ItemFamily family1 = new ItemFamily("family1", null, null, null, "brand1");
        Question question1 = new Question(1,1,1,"QuestionTest", 10,null, family1, null);
        Question question2 = new Question(2, 2, 2, "Second Question", 55, null, family1, null);
        itemFamilyRepo.saveAndFlush(family1);
        questionRepo.saveAndFlush(question1);
        questionRepo.saveAndFlush(question2);

        List<ItemFamily> families = itemFamilyService.getAllItemFamilies();
        List<Question> questions = questionService.getAllQuestions();

        assertEquals(1, families.size());
        assertEquals(2, questions.size());

        Answer answer1 = new Answer(1, 1, "A", null, question1);
        Answer answer2 = new Answer(2, 2, "B", null, question1);
        Answer answer3 = new Answer(3,3,"C", null, question1);
        answerRepo.saveAndFlush(answer1);
        answerRepo.saveAndFlush(answer2);
        answerRepo.saveAndFlush(answer3);
        List<Answer> answers = answerService.getAllAnswers();
        assertEquals(3, answers.size());

        Answer answer4 = new Answer(4, 8, "D", null, question2);
        Answer answer5 = new Answer(5, 2, "E", null, question2);
        Answer answer6 = new Answer(6, 1, "F", null, question2);
        Answer answer7 = new Answer(7, 99, "G", null, question2);
        answerRepo.saveAndFlush(answer4);
        answerRepo.saveAndFlush(answer5);
        answerRepo.saveAndFlush(answer6);
        answerRepo.saveAndFlush(answer7);

        List<Answer> answersForQuestion1 = answerService.getByQuestionQuestionId(1);
        List<Answer> answersForQuestion2 = answerService.getByQuestionQuestionId(2);
        assertEquals(3, answersForQuestion1.size());
        assertEquals(4, answersForQuestion2.size());
    }

}
