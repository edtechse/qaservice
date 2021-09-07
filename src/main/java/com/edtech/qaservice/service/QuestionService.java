package com.edtech.qaservice.service;

import java.util.ArrayList;
import java.util.List;

import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.repository.QARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QARepository questionRepository;

    @Autowired
    public QuestionService(QARepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionItem getQuestionById(String id) {
        QuestionItem question = questionRepository.findQuestionById(id);
        return question;
    }

    public void postQuestionByQuestionItem(QuestionItem questionItem) {
        questionRepository.createQuestionByQuestionItem(questionItem);
    }
}
