package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.models.Question;
import com.talentpath.shamazin.showItemPage.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Integer id){return service.getQuestionById(id);}

    @PostMapping("/")
    public Question addQuestion(@RequestBody Question toAdd){return service.addQuestion(toAdd);}

    @PutMapping("/")
    public Question editQuestion(@RequestBody Question edited){return service.editQuestion(edited);}

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Integer id){service.deleteQuestion(id);}

}
