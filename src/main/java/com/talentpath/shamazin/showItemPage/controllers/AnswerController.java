package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.exceptions.NullAnswerException;
import com.talentpath.shamazin.showItemPage.models.Answer;
import com.talentpath.shamazin.showItemPage.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    AnswerService service;

    @GetMapping("/getAll")
    public List<Answer> getAllAnswers(){return service.getAllAnswers();}

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Integer id) throws NullAnswerException {return service.getAnswerById(id);}

    @PostMapping("/")
    public Answer addAnswer(@RequestBody Answer toAdd){return service.addAnswer(toAdd);}

    @PutMapping("/")
    public Answer editAnswer(@RequestBody Answer edited){return service.editAnswer(edited);}

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Integer id){service.deleteAnswerById(id);}
}
