package com.edtech.qaservice.controller;

import com.edtech.qaservice.model.AnswerItem;
import com.edtech.qaservice.service.QAService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edtech/answer")
public class AnswerController {

    @Autowired
    private QAService qaService;

    @PostMapping("author/{author}")
    public String createAnswerIntoDynamoDB(@PathVariable(value = "author") String author, @RequestBody AnswerItem answerItem) {
        try {
            answerItem.setAuthor(author);
            qaService.postAnswerByAnswerItem(answerItem);
            return "Successfully inserted answer into QAService table";
        } catch (Exception e) {
            return "Failed to inserted into QAService table due to " + e.getMessage();
        }

    }

    @GetMapping("author/{author}")
    public List<String> getAnswerIdByAuthor(@PathVariable(value = "author") String author)
    {
        return qaService.findAnswerIdByAuthor(author);
    }

    @DeleteMapping("author/{author}")
    public String deleteAnswerByAuthor(@PathVariable(value = "author") String author) {
        try {
            qaService.deleteAnswerByAuthor(author);
            return "Successfully delete all answer by author in QAService table";
        } catch (Exception e) {
            return "Failed to delete all answer by author in QAService table due to " + e.getMessage();
        }
    }

    @GetMapping("id/{id}")
    public AnswerItem getAnswerById(@PathVariable(value = "id") String id)
    {
        return qaService.getAnswerById(id);
    }

    @DeleteMapping("id/{id}")
    public String deleteAnswerById(@PathVariable(value = "id") String id) {
        try {
            qaService.deleteAnswerById(id);
            return "Successfully delete specific answer in QAService table";
        } catch (Exception e) {
            return "Failed to delete answer in QAService table due to " + e.getMessage();
        }
    }
}
