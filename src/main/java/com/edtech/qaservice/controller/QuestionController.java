package com.edtech.qaservice.controller;

import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.service.QuestionService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edtech/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("author/{author}")
    public String createQuestionIntoDynamoDB(@PathVariable(value = "author") String author, @RequestBody QuestionItem questionItem) {
        try {
            questionItem.setAuthor(author);
            questionService.postQuestionByQuestionItem(questionItem);
            return "Successfully inserted question into QAService table";
        } catch (Exception e) {
            return "Failed to inserted into DynamoDB table due to " + e.getMessage();
        }

    }

    @PutMapping("author/{author}")
    public String updateQuestionIntoDynamoDB(@PathVariable(value = "author") String author, @RequestBody QuestionItem questionItem) {
        try {
            questionItem.setAuthor(author);
            questionService.updateQuestionItem(questionItem);
            return "Successfully modify question in QAService table";
        } catch (Exception e) {
            return "Failed to modify DynamoDB table due to " + e.getMessage();
        }
    }

    @GetMapping("author/{author}")
    public List<String> getQuestionIdByAuthor(@PathVariable(value = "author") String author)
    {
        return questionService.findQuestionIdByAuthor(author);
    }

    @GetMapping("id/{id}")
    public QuestionItem getQuestionById(@PathVariable(value = "id") String id)
    {
        return questionService.getQuestionById(id);
    }
}
