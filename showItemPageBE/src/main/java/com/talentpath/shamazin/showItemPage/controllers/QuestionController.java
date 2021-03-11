package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.models.Question;
import com.talentpath.shamazin.showItemPage.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService service;

    @GetMapping("/getAll")
    public List<Question> getAllQuestions() {
        return service.getAllQuestions();
    }

}
