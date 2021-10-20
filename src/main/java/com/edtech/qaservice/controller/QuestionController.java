package com.edtech.qaservice.controller;

import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.service.QAService;

import java.util.List;

import com.edtech.qaservice.util.QAServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edtech/question")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionController {

    @Autowired
    private QAService qaService;

    @GetMapping("all")
    public List<QuestionItem> getAllQuestion()
    {
        return qaService.getAllQuestion();
    }

    @PostMapping("author/{author}")
    public String createQuestionIntoDynamoDB(@PathVariable(value = "author") String author, @RequestBody QuestionItem questionItem) {
        try {
            QAServiceUtil.checkIfAnswerHasId(questionItem);
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
            QAServiceUtil.checkIfAnswerHasId(questionItem);
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
        return qaService.getQuestionIdByAuthor(author);
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

    @GetMapping("id/{questionId}")
    public QuestionItem getQuestionById(@PathVariable(value = "questionId") String questionId)
    {
        return qaService.getQuestionById(questionId);
    }

    @DeleteMapping("id/{questionId}")
    public String deleteQuestionById(@PathVariable(value = "questionId") String questionId) {
        try {
            qaService.deleteQuestionById(questionId);
            return "Successfully delete specific question by questionId in QAService table";
        } catch (Exception e) {
            return "Failed to delete question in QAService table due to " + e.getMessage();
        }
    }
}
