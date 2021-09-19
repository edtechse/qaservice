package com.edtech.qaservice.service;

import java.util.*;

import com.edtech.qaservice.model.AnswerItem;
import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.repository.QARepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public List<QuestionItem> getAllQuestion() {
        return qaRepository.findAllQuestion();
    }

    public void deleteQuestionById(String id) {
        QuestionItem question = qaRepository.findQuestionById(id);
        qaRepository.deleteQuestionByQuestionItem(question);
    }

    public void postQuestionByQuestionItem(QuestionItem questionItem) {
        qaRepository.saveQuestionByQuestionItem(questionItem);
    }

    public void updateQuestionItem(QuestionItem questionItem) {
        QuestionItem originQuestion = qaRepository.findQuestionById(questionItem.getQuestionId());
        if(questionItem.getQuestionText() != null)
            originQuestion.setQuestionText(questionItem.getQuestionText());
        if(questionItem.getQuestionTag() != null)
            originQuestion.setQuestionTag(questionItem.getQuestionTag());
        qaRepository.saveQuestionByQuestionItem(originQuestion);
    }

    public List<String> getQuestionIdByAuthor(String author) {
        List<QuestionItem> items = qaRepository.findQuestionByAuthor(author);
        List<String> idList = new ArrayList<>();
        for(QuestionItem item: items) {
            idList.add(item.getQuestionId());
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
    public AnswerItem getAnswerById(String questionId, String answerId) {
        QuestionItem questionItem = qaRepository.findQuestionById(questionId);
        List<AnswerItem> answerList = questionItem.getAnswers();

        AnswerItem answerItem = new AnswerItem();
        ObjectMapper mapper = new ObjectMapper();
        for (Object answerBeforeConvert: answerList) {
            AnswerItem answer = mapper.convertValue(answerBeforeConvert, AnswerItem.class);
            if(answer.getAnswerId().equals(answerId)) {
                answerItem = answer;
                break;
            }
        }
        return answerItem;
    }

    public void deleteAnswerById(String questionId, String answerId) {
        QuestionItem questionItem = qaRepository.findQuestionById(questionId);
        List<AnswerItem> answerList = questionItem.getAnswers();
        List<AnswerItem> newAnswerList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for(Object answerBeforeConvert: answerList) {
            AnswerItem answer = mapper.convertValue(answerBeforeConvert, AnswerItem.class);
            if(!answer.getAnswerId().equals(answerId))
                newAnswerList.add(answer);
        }
        questionItem.setAnswers(newAnswerList);
        qaRepository.saveQuestionByQuestionItem(questionItem);
    }

    public void postAnswerByAnswerItem(String questionId, AnswerItem answerItem) {
        QuestionItem questionItem = qaRepository.findQuestionById(questionId);
        List<AnswerItem> answerList = questionItem.getAnswers();
        answerList.add(answerItem);
        questionItem.setAnswers(answerList);
        qaRepository.saveQuestionByQuestionItem(questionItem);
    }

    public List<AnswerItem> getAnswerByAuthor(String author) {
        List<QuestionItem> questionList = qaRepository.findAllQuestion();
        List<AnswerItem> authorAnswerList = new ArrayList<>();

        for(QuestionItem questionItem: questionList) {
            List<AnswerItem> answerList = questionItem.getAnswers();

            ObjectMapper mapper = new ObjectMapper();
            for(Object answerBeforeConvert: answerList) {
                AnswerItem answer = mapper.convertValue(answerBeforeConvert, AnswerItem.class);
                if(answer.getAuthor().equals(author)) {
                    authorAnswerList.add(answer);
                }
            }
        }
        return authorAnswerList;
    }
}
