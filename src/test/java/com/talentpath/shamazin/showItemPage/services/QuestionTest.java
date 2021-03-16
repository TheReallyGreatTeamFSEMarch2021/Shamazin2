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
        //assertEquals(question1,questions.get(0));


    }
}
