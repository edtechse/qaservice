package com.edtech.qaservice.service;

import java.util.ArrayList;
import java.util.List;

import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionItem getQuestionById(int questionId) {
        QuestionItem question = questionRepository.findQuestionById(questionId);
        return question;
    }
}
