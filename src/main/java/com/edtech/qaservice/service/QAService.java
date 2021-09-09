package com.edtech.qaservice.service;

import java.util.ArrayList;
import java.util.List;

import com.edtech.qaservice.model.AnswerItem;
import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.repository.QARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QAService {

    private QARepository qaRepository;

    @Autowired
    public QAService(QARepository questionRepository) {
        this.qaRepository = questionRepository;
    }

    // CRUD operation for question
    public QuestionItem getQuestionById(String id) {
        QuestionItem question = qaRepository.findQuestionById(id);
        return question;
    }

    public void deleteQuestionById(String id) {
        QuestionItem question = qaRepository.findQuestionById(id);
        qaRepository.deleteQuestionByQuestionItem(question);
    }

    public void postQuestionByQuestionItem(QuestionItem questionItem) {
        qaRepository.saveQuestionByQuestionItem(questionItem);
    }

    public void updateQuestionItem(QuestionItem questionItem) {
        QuestionItem originQuestion = qaRepository.findQuestionById(questionItem.getId());
        if(questionItem.getQuestionText() != null)
            originQuestion.setQuestionText(questionItem.getQuestionText());
        if(questionItem.getQuestionTag() != null)
            originQuestion.setQuestionTag(questionItem.getQuestionTag());
        qaRepository.saveQuestionByQuestionItem(originQuestion);
    }

    public List<String> findQuestionIdByAuthor(String author) {
        List<QuestionItem> items = qaRepository.findQuestionByAuthor(author);
        List<String> idList = new ArrayList<>();
        for(QuestionItem item: items) {
            idList.add(item.getId());
        }
        return idList;
    }

    public void deleteQuestionByAuthor(String author) {
        List<QuestionItem> items = qaRepository.findQuestionByAuthor(author);
        for(QuestionItem item: items) {
            qaRepository.deleteQuestionByQuestionItem(item);
        }
    }

    // CRUD operation for answer
    public AnswerItem getAnswerById(String id) {
        AnswerItem answerItem = qaRepository.findAnswerById(id);
        return answerItem;
    }

    public void deleteAnswerById(String id) {
        AnswerItem answerItem = qaRepository.findAnswerById(id);
        qaRepository.deleteAnswerByAnswerItem(answerItem);
    }

    public void postAnswerByAnswerItem(AnswerItem answerItem) {
        qaRepository.saveAnswerByAnswerItem(answerItem);
    }

    public List<String> findAnswerIdByAuthor(String author) {
        List<AnswerItem> items = qaRepository.findAnswerByAuthor(author);
        List<String> idList = new ArrayList<>();
        for(AnswerItem item: items) {
            idList.add(item.getId());
        }
        return idList;
    }

    public void deleteAnswerByAuthor(String author) {
        List<AnswerItem> items = qaRepository.findAnswerByAuthor(author);
        for(AnswerItem item: items) {
            qaRepository.deleteAnswerByAnswerItem(item);
        }
    }
}
