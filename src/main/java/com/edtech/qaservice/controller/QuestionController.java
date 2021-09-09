package com.edtech.qaservice.controller;

import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.service.QAService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edtech/question")
public class QuestionController {

    @Autowired
    private QAService qaService;

    @PostMapping("author/{author}")
    public String createQuestionIntoDynamoDB(@PathVariable(value = "author") String author, @RequestBody QuestionItem questionItem) {
        try {
            questionItem.setAuthor(author);
            qaService.postQuestionByQuestionItem(questionItem);
            return "Successfully inserted question into QAService table";
        } catch (Exception e) {
            return "Failed to inserted into QAService table due to " + e.getMessage();
        }

    }

    @PutMapping("author/{author}")
    public String updateQuestionIntoDynamoDB(@PathVariable(value = "author") String author, @RequestBody QuestionItem questionItem) {
        try {
            questionItem.setAuthor(author);
            qaService.updateQuestionItem(questionItem);
            return "Successfully modify question in QAService table";
        } catch (Exception e) {
            return "Failed to modify QAService table due to " + e.getMessage();
        }
    }

    @GetMapping("author/{author}")
    public List<String> getQuestionIdByAuthor(@PathVariable(value = "author") String author)
    {
        return qaService.findQuestionIdByAuthor(author);
    }

    @DeleteMapping("author/{author}")
    public String deleteQuestionByAuthor(@PathVariable(value = "author") String author) {
        try {
            qaService.deleteQuestionByAuthor(author);
            return "Successfully delete all question by author in QAService table";
        } catch (Exception e) {
            return "Failed to delete all question by author in QAService table due to " + e.getMessage();
        }
    }

    @GetMapping("id/{id}")
    public QuestionItem getQuestionById(@PathVariable(value = "id") String id)
    {
        return qaService.getQuestionById(id);
    }

    @DeleteMapping("id/{id}")
    public String deleteQuestionById(@PathVariable(value = "id") String id) {
        try {
            qaService.deleteQuestionById(id);
            return "Successfully delete specific question in QAService table";
        } catch (Exception e) {
            return "Failed to delete question in QAService table due to " + e.getMessage();
        }
    }
}
