package com.edtech.qaservice.controller;

import com.edtech.qaservice.model.AnswerItem;
import com.edtech.qaservice.service.QAService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edtech/answer")
public class AnswerController {

    @Autowired
    private QAService qaService;

    @PostMapping("questionid/{questionId}")
    public String createAnswerIntoDynamoDB(@PathVariable(value = "questionId") String questionId, @RequestBody AnswerItem answerItem) {
        try {
            String answerId = UUID.randomUUID().toString().replaceAll("-","");
            answerItem.setAnswerId(answerId);
            qaService.postAnswerByAnswerItem(questionId, answerItem);
            return "Successfully inserted answer into QAService table";
        } catch (Exception e) {
            return "Failed to inserted into QAService table due to " + e.getMessage();
        }

    }

    @GetMapping("questionid/{questionId}/answerid/{answerId}")
    public AnswerItem getAnswerById(@PathVariable(value = "questionId") String questionId, @PathVariable(value = "answerId") String answerId) {
        return qaService.getAnswerById(questionId, answerId);
    }

    @DeleteMapping("questionid/{questionId}/answerid/{answerId}")
    public String deleteAnswerById(@PathVariable(value = "questionId") String questionId, @PathVariable(value = "answerId") String answerId) {
        try {
            qaService.deleteAnswerById(questionId,answerId );
            return "Successfully delete specific answer in QAService table";
        } catch (Exception e) {
            return "Failed to delete answer in QAService table due to " + e.getMessage();
        }
    }

    @GetMapping("author/{author}")
    public List<AnswerItem> getAnswerByAuthor(@PathVariable(value = "author") String author) {
        return qaService.getAnswerByAuthor(author);
    }
}
