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

    public void deleteQuestionById(String id) {
        QuestionItem question = questionRepository.findQuestionById(id);
        questionRepository.deleteQuestionByQuestionItem(question);
    }

    public void postQuestionByQuestionItem(QuestionItem questionItem) {
        questionRepository.saveQuestionByQuestionItem(questionItem);
    }

    public void updateQuestionItem(QuestionItem questionItem) {
        QuestionItem originQuestion = questionRepository.findQuestionById(questionItem.getId());
        if(questionItem.getQuestionText() != null)
            originQuestion.setQuestionText(questionItem.getQuestionText());
        if(questionItem.getQuestionTag() != null)
            originQuestion.setQuestionTag(questionItem.getQuestionTag());
        questionRepository.saveQuestionByQuestionItem(originQuestion);
    }

    public List<String> findQuestionIdByAuthor(String author) {
        List<QuestionItem> items = questionRepository.findQuestionByAuthor(author);
        List<String> idList = new ArrayList<>();
        for(QuestionItem item: items) {
            idList.add(item.getId());
        }
        return idList;
    }

    public void deleteQuestionByAuthor(String author) {
        List<QuestionItem> items = questionRepository.findQuestionByAuthor(author);
        for(QuestionItem item: items) {
            questionRepository.deleteQuestionByQuestionItem(item);
        }
    }
}
